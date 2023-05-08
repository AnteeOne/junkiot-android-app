package tech.antee.junkiot.simulator.light_sensor.impl.ui

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import tech.antee.junkiot.controll.common.usecases.ObserveSimulatorUsecase
import tech.antee.junkiot.controll.light_sensor.models.Lux
import tech.antee.junkiot.controll.light_sensor.usecases.AddLightSensorValueUsecase
import tech.antee.junkiot.controll.light_sensor.usecases.ObserveLightPredictionsUsecase
import tech.antee.junkiot.simulator.light_sensor.impl.di.ControllerId
import tech.antee.junkiot.simulator.light_sensor.impl.ui.items.Action
import tech.antee.junkiot.simulator.light_sensor.impl.ui.items.Event
import tech.antee.junkiot.simulator.light_sensor.impl.ui.items.UiState
import tech.antee.junkiot.simulator.light_sensor.impl.ui.mappers.SimulatorUiMapper
import tech.antee.junkiot.simulator.light_sensor.managers.LightSensorManager
import tech.antee.junkiot.simulator.light_sensor.models.LightSensorState
import tech.antee.junkiot.ui.BaseViewModel
import javax.inject.Inject

class LightSensorSimulatorViewModel @Inject constructor(
    @ControllerId private val controllerId: Int,
    private val observeSimulatorUsecase: ObserveSimulatorUsecase,
    private val addLightSensorValueUsecase: AddLightSensorValueUsecase,
    private val observeLightPredictionsUsecase: ObserveLightPredictionsUsecase,
    private val lightSensorManager: LightSensorManager,
    private val mapper: SimulatorUiMapper
) : BaseViewModel<UiState, Event, Action>() {

    override val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.empty())

    private var lightSensorValuesObservingJob: Job? = null

    init {
        observeSimulator()
        observeLightSensorManagerState()
    }

    override fun onAction(action: Action) {
        when (action) {
            Action.OnStartBtnClick -> onStartBtnClick()
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

    private fun onStartBtnClick() {
        when (lightSensorValuesObservingJob) {
            null -> observeLightSensorValues()
            else -> clearSensorValuesObservingJob()
        }
    }

    private fun observeLightSensorValues() {
        if (lightSensorValuesObservingJob == null) {
            lightSensorValuesObservingJob = launchSafely {
                launchSafely(
                    scope = this,
                    onError = ::onObservingLightSensorValuesError
                ) {
                    lightSensorManager.lightSensorValues.collect { lightSensorState ->
                        updateState { it.withLightSensorState(mapper.map(lightSensorState)) }
                        if (lightSensorState is LightSensorState.Value) {
                            addLightSensorValueUsecase(controllerId, Lux(lightSensorState.luxes.last().toInt()))
                        }
                    }
                }
                launchSafely(
                    scope = this,
                    onError = ::onObservingLightSensorValuesError
                ) {
                    observeLightPredictionsUsecase(controllerId).collect { predictions ->
                        updateState { it.withLightPredictionState(mapper.map(predictions.lastOrNull())) }
                    }
                }
            }
        }
    }

    private fun onBackBtnClick() {
        emitEvent(Event.NavBack)
    }

    private fun observeLightSensorManagerState() {
        launchSafely {
            lightSensorManager.lightSensorManagerState.collect { lightSensorManagerState ->
                updateState { it.withLightSensorManagerState(lightSensorManagerState) }
            }
        }
    }

    fun onObservingLightSensorValuesError(t: Throwable) {
        clearSensorValuesObservingJob()
        emitEvent(Event.ShowErrorSnackBar)
    }

    fun clearSensorValuesObservingJob() {
        lightSensorValuesObservingJob?.cancel()
        lightSensorValuesObservingJob = null
    }
}
