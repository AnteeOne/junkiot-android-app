package tech.antee.junkiot.simulator.light_sensor.impl.ui

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import tech.antee.junkiot.controll.common.usecases.GetReactiveSimulatorUsecase
import tech.antee.junkiot.simulator.light_sensor.impl.di.ControllerId
import tech.antee.junkiot.simulator.light_sensor.impl.ui.items.Action
import tech.antee.junkiot.simulator.light_sensor.impl.ui.items.Event
import tech.antee.junkiot.simulator.light_sensor.impl.ui.items.UiState
import tech.antee.junkiot.simulator.light_sensor.impl.ui.mappers.SimulatorUiMapper
import tech.antee.junkiot.simulator.light_sensor.managers.LightSensorManager
import tech.antee.junkiot.ui.BaseViewModel
import javax.inject.Inject

class LightSensorSimulatorViewModel @Inject constructor(
    @ControllerId private val controllerId: Int,
    private val getReactiveSimulatorUsecase: GetReactiveSimulatorUsecase,
    private val lightSensorManager: LightSensorManager,
    private val simulatorUiMapper: SimulatorUiMapper
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
        }
    }

    private fun observeSimulator() {
        launchSafely {
            getReactiveSimulatorUsecase(controllerId)
                .onStart { updateState { it.withLoading(true) } }
                .collect { controller ->
                    updateState { it.withSimulatorItem(simulatorUiMapper.map(controller)) }
                }
        }
    }

    private fun onStartBtnClick() {
        when (lightSensorValuesObservingJob) {
            null -> observeLightSensorValues()
            else -> {
                lightSensorValuesObservingJob?.cancel()
                lightSensorValuesObservingJob = null
            }
        }
    }

    private fun observeLightSensorValues() {
        if (lightSensorValuesObservingJob == null) {
            lightSensorValuesObservingJob = launchSafely {
                lightSensorManager.lightSensorValues.collect { lightSensorState ->
                    updateState { it.withLightSensorState(lightSensorState) }
                }
            }
        }
    }

    private fun observeLightSensorManagerState() {
        launchSafely {
            lightSensorManager.lightSensorManagerState.collect { lightSensorManagerState ->
                updateState { it.withLightSensorManagerState(lightSensorManagerState) }
            }
        }
    }
}
