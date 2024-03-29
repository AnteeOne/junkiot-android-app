package tech.antee.junkiot.simulator.list.impl.ui

import kotlinx.coroutines.flow.MutableStateFlow
import tech.antee.junkiot.controll.common.usecases.AddControllerUsecase
import tech.antee.junkiot.controll.common.usecases.ObserveSimulatorsUsecase
import tech.antee.junkiot.simulator.list.impl.ui.items.Action
import tech.antee.junkiot.simulator.list.impl.ui.items.Event
import tech.antee.junkiot.simulator.list.impl.ui.items.UiState
import tech.antee.junkiot.simulator.list.impl.ui.mappers.SimulatorUiMapper
import tech.antee.junkiot.ui.BaseViewModel
import javax.inject.Inject

class SimulatorListViewModel @Inject constructor(
    private val observeSimulatorsUsecase: ObserveSimulatorsUsecase,
    private val addControllerUsecase: AddControllerUsecase,
    private val simulatorUiMapper: SimulatorUiMapper
) : BaseViewModel<UiState, Event, Action>() {

    override val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.empty())

    init {
        observeSimulators()
    }

    override fun onAction(action: Action) {
        when (action) {
            is Action.OnItemClick -> emitEvent(Event.OnNavToDetails(action.id, action.controllerType))
        }
    }

    private fun observeSimulators() {
        launchSafely {
            observeSimulatorsUsecase().collect { items ->
                updateState { it.withSuccess(items.map(simulatorUiMapper::map)) }
            }
        }
    }
}
