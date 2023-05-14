package tech.antee.junkiot.simulator.claps_detector.impl.ui.mappers

import tech.antee.junkiot.controll.common.models.Controller
import tech.antee.junkiot.simulator.claps_detector.impl.ui.items.SimulatorItem
import javax.inject.Inject

class SimulatorUiMapper @Inject constructor() {

    fun map(controller: Controller): SimulatorItem = with(controller) {
        SimulatorItem(
            controllerType = controllerType,
            id = id,
            name = name,
            isOnline = isOnline
        )
    }
}