package tech.antee.junkiot.simulator.list.impl.ui.items

import tech.antee.junkiot.controll.common.models.ControllerType

sealed interface Action {

    data class OnItemClick(val id: Int, val controllerType: ControllerType) : Action
}
