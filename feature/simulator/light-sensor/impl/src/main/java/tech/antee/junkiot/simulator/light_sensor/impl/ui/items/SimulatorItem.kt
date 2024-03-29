package tech.antee.junkiot.simulator.light_sensor.impl.ui.items

import tech.antee.junkiot.controll.common.models.ControllerType

data class SimulatorItem(
    val controllerType: ControllerType,
    val id: Int,
    val name: String,
    val isOnline: Boolean
)
