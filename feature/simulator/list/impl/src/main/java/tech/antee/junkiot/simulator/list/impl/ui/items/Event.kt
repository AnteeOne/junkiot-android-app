package tech.antee.junkiot.simulator.list.impl.ui.items

import tech.antee.junkiot.controll.common.models.ControllerType

sealed interface Event {
    data class OnNavToDetails(val id: Int, val controllerType: ControllerType) : Event
}
