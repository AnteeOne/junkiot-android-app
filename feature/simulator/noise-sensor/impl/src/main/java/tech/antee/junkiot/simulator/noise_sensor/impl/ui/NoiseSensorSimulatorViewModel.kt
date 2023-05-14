package tech.antee.junkiot.simulator.noise_sensor.impl.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import tech.antee.junkiot.controll.common.usecases.ObserveSimulatorUsecase
import tech.antee.junkiot.di.qualifiers.ControllerId
import tech.antee.junkiot.simulator.noise_sensor.impl.ui.items.Action
import tech.antee.junkiot.simulator.noise_sensor.impl.ui.items.Event
import tech.antee.junkiot.simulator.noise_sensor.impl.ui.items.UiState
import tech.antee.junkiot.simulator.noise_sensor.impl.ui.mappers.SimulatorUiMapper
import tech.antee.junkiot.ui.BaseViewModel
import javax.inject.Inject

class NoiseSensorSimulatorViewModel @Inject constructor(
    @ControllerId private val controllerId: Int,
    private val observeSimulatorUsecase: ObserveSimulatorUsecase,
    private val mapper: SimulatorUiMapper
) : BaseViewModel<UiState, Event, Action>() {

    override val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.empty())

    init {
        observeSimulator()
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
    }

    private fun onBackBtnClick() {
        emitEvent(Event.NavBack)
    }
}
