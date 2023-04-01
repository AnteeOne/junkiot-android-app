package tech.antee.junkiot.controll.impl.common.local.entities

import tech.antee.junkiot.controll.common.models.ControllerType

data class ControllerEntity(
    val controllerType: ControllerType,
    val id: Int,
    val name: String,
    val isOnline: Boolean
)
