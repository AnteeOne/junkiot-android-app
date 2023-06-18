package tech.antee.junkiot.simulator.claps_detector.impl.ui

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import tech.antee.junkiot.controll.claps_detector.usecases.ObserveClapDetectionsUsecase
import tech.antee.junkiot.controll.claps_detector.usecases.AddClapDetectionUsecase
import tech.antee.junkiot.controll.common.usecases.ObserveSimulatorUsecase
import tech.antee.junkiot.di.qualifiers.ControllerId
import tech.antee.junkiot.simulator.claps_detector.impl.ui.items.Action
import tech.antee.junkiot.simulator.claps_detector.impl.ui.items.Event
import tech.antee.junkiot.simulator.claps_detector.impl.ui.items.UiState
import tech.antee.junkiot.simulator.claps_detector.impl.ui.mappers.SimulatorUiMapper
import tech.antee.junkiot.tensorflow_bridge.audio.services.claps.ClapsDetectionState
import tech.antee.junkiot.tensorflow_bridge.audio.services.claps.ClapsDetector
import tech.antee.junkiot.ui.BaseViewModel
import javax.inject.Inject

class ClapsDetectorSimulatorViewModel @Inject constructor(
    @ControllerId private val controllerId: Int,
    private val observeSimulatorUsecase: ObserveSimulatorUsecase,
    private val observeClapDetectionsUsecase: ObserveClapDetectionsUsecase,
    private val addClapDetectionUsecase: AddClapDetectionUsecase,
    private val clapsDetector: ClapsDetector,
    private val mapper: SimulatorUiMapper
) : BaseViewModel<UiState, Event, Action>() {

    override val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.empty())

    private var clapsDetectorValuesObservingJob: Job? = null

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

    private fun observeClapsDetections() {
        if (clapsDetectorValuesObservingJob == null) {
            clapsDetector.start()
            clapsDetectorValuesObservingJob = launchSafely {
                launchSafely(
                    scope = this
                ) {
                    clapsDetector.clapsDetectionState.collect { clapState ->
                        updateState { state ->
                            state.withClapState(clapState is ClapsDetectionState.Clap)
                        }
                        if (clapState is ClapsDetectionState.Clap) addClapDetectionUsecase(controllerId)
                    }
                }
                launchSafely(
                    scope = this
                ) {
                    observeClapDetectionsUsecase(controllerId).collect { clapDetections ->
                        updateState { it.withClapDetections(clapDetections.takeLast(6)) }
                    }
                }
            }
        }
    }

    private fun observeDetectorState() {
        launchSafely {
            clapsDetector.detectorState.collect { detectorState ->
                updateState { state -> state.withDetectorState(mapper.map(detectorState)) }
            }
        }
    }

    private fun onDetectBtnClick() {
        when (clapsDetectorValuesObservingJob) {
            null -> observeClapsDetections()
            else -> clearClapDetectionValuesObservingJob()
        }
    }

    private fun onBackBtnClick() {
        emitEvent(Event.NavBack)
    }

    override fun onCleared() {
        super.onCleared()
        clearClapDetectionValuesObservingJob()
    }

    fun clearClapDetectionValuesObservingJob() {
        clapsDetector.stop()
        clapsDetectorValuesObservingJob?.cancel()
        clapsDetectorValuesObservingJob = null
    }
}
