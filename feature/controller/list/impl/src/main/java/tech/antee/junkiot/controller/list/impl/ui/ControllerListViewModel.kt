package tech.antee.junkiot.controller.list.impl.ui

import kotlinx.coroutines.flow.MutableStateFlow
import tech.antee.junkiot.controll.common.usecases.GetReactiveControllersUsecase
import tech.antee.junkiot.controll.common.usecases.ObserveRemoteControllersUsecase
import tech.antee.junkiot.controller.list.impl.ui.items.Action
import tech.antee.junkiot.controller.list.impl.ui.items.Event
import tech.antee.junkiot.controller.list.impl.ui.items.UiState
import tech.antee.junkiot.controller.list.impl.ui.mappers.ControllerUiMapper
import tech.antee.junkiot.ui.BaseViewModel
import javax.inject.Inject

class ControllerListViewModel @Inject constructor(
    private val getReactiveControllersUsecase: GetReactiveControllersUsecase,
    private val observeRemoteControllersUsecase: ObserveRemoteControllersUsecase,
    private val controllerUiMapper: ControllerUiMapper
) : BaseViewModel<UiState, Event, Action>() {

    override val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.empty())

    init {
        observeControllers()
    }

    override fun onAction(action: Action) {
        when (action) {
            is Action.OnItemClick -> {}
        }
    }

    private fun observeControllers() {
        launchSafely {
            observeRemoteControllersUsecase()
        }
        launchSafely {
            getReactiveControllersUsecase().collect { controllers ->
                updateState { it.withSuccess(controllers.map(controllerUiMapper::map)) }
            }
        }
    }
}
