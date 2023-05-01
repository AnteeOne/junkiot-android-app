package tech.antee.junkiot.controll.impl.common.remote.dto

data class ControllerDto(
    val controllerTypeId: Int,
    val id: Int,
    val name: String,
    val isOnline: Boolean
)
