package tech.antee.junkiot.simulator.list.impl.ui.mappers

import tech.antee.junkiot.controll.common.models.Controller
import tech.antee.junkiot.simulator.list.impl.ui.items.SimulatorItem
import javax.inject.Inject

class SimulatorUiMapper @Inject constructor() {

    fun map(model: Controller): SimulatorItem = with(model) {
        SimulatorItem(controllerType, id, name)
    }
}
