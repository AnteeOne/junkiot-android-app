package tech.antee.junkiot.simulator.noise_sensor.impl.ui

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import tech.antee.junkiot.controll.common.usecases.ObserveSimulatorUsecase
import tech.antee.junkiot.controll.noise_detector.usecases.AddNoiseDetectionUsecase
import tech.antee.junkiot.controll.noise_detector.usecases.ObserveNoiseDetectionsUsecase
import tech.antee.junkiot.di.qualifiers.ControllerId
import tech.antee.junkiot.simulator.noise_sensor.impl.ui.items.Action
import tech.antee.junkiot.simulator.noise_sensor.impl.ui.items.Event
import tech.antee.junkiot.simulator.noise_sensor.impl.ui.items.UiState
import tech.antee.junkiot.simulator.noise_sensor.impl.ui.mappers.SimulatorUiMapper
import tech.antee.junkiot.tensorflow_bridge.audio.services.noise.NoiseDetectionState
import tech.antee.junkiot.tensorflow_bridge.audio.services.noise.NoiseDetector
import tech.antee.junkiot.ui.BaseViewModel
import javax.inject.Inject

class NoiseSensorSimulatorViewModel @Inject constructor(
    @ControllerId private val controllerId: Int,
    private val noiseDetector: NoiseDetector,
    private val observeSimulatorUsecase: ObserveSimulatorUsecase,
    private val observeNoiseDetectionsUsecase: ObserveNoiseDetectionsUsecase,
    private val addNoiseDetectionUsecase: AddNoiseDetectionUsecase,
    private val mapper: SimulatorUiMapper
) : BaseViewModel<UiState, Event, Action>() {

    override val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.empty())

    private var noiseDetectorValuesObservingJob: Job? = null

    init {
        observeSimulator()
        observeDetectorState()
    }

    override fun onAction(action: Action) {
        when (action) {
            Action.OnDetectBtnClick -> onDetectBtnClick()
            Action.OnBackBtnClick -> onBackBtnClick()
        }
    }

    private fun observeSimulator() {
        launchSafely {
            observeSimulatorUsecase(controllerId)
                .onStart { updateState { it.withLoading(true) } }
                .collect { controller ->
                    updateState { it.withSimulatorItem(mapper.map(controller)) }
                }
        }
    }

    private fun observeNoiseDetections() {
        if (noiseDetectorValuesObservingJob == null) {
            noiseDetector.start()
            noiseDetectorValuesObservingJob = launchSafely {
                launchSafely(
                    scope = this
                ) {
                    noiseDetector.noiseDetectionState.collect { noiseDetectionState ->
                        if (noiseDetectionState is NoiseDetectionState.Noise) {
                            updateState { state ->
                                state.withNoiseDetectionState(mapper.map(noiseDetectionState))
                            }
                            addNoiseDetectionUsecase(controllerId, noiseDetectionState.label.labelValue)
                        }
                    }
                }
                launchSafely(
                    scope = this
                ) {
                    observeNoiseDetectionsUsecase(controllerId).collect { noiseDetections ->
                        updateState { it.withNoiseDetections(noiseDetections.takeLast(6)) }
                    }
                }
            }
        }
    }

    private fun observeDetectorState() {
        launchSafely {
            noiseDetector.detectorState.collect { detectorState ->
                updateState { state -> state.withDetectorState(mapper.map(detectorState)) }
            }
        }
    }

    private fun onDetectBtnClick() {
        when (noiseDetectorValuesObservingJob) {
            null -> observeNoiseDetections()
            else -> clearNoiseDetectionValuesObservingJob()
        }
    }

    private fun onBackBtnClick() {
        emitEvent(Event.NavBack)
    }

    override fun onCleared() {
        super.onCleared()
        clearNoiseDetectionValuesObservingJob()
    }

    fun clearNoiseDetectionValuesObservingJob() {
        noiseDetector.stop()
        noiseDetectorValuesObservingJob?.cancel()
        noiseDetectorValuesObservingJob = null
    }
}
