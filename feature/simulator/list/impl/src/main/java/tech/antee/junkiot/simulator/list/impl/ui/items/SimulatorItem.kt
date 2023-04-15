package tech.antee.junkiot.simulator.list.impl.ui.items

import tech.antee.junkiot.controll.common.models.ControllerType

data class SimulatorItem(
    val controllerType: ControllerType,
    val id: Int,
    val name: String
)
