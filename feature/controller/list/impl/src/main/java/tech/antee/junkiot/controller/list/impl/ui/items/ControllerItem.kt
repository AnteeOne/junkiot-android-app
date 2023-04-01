package tech.antee.junkiot.controller.list.impl.ui.items

import tech.antee.junkiot.controll.common.models.ControllerType

data class ControllerItem(
    val controllerType: ControllerType,
    val id: Int,
    val name: String,
    val isOnline: Boolean
)
