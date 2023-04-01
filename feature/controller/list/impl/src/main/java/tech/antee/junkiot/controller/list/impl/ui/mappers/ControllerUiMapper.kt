package tech.antee.junkiot.controller.list.impl.ui.mappers

import tech.antee.junkiot.controll.common.models.Controller
import tech.antee.junkiot.controller.list.impl.ui.items.ControllerItem
import javax.inject.Inject

class ControllerUiMapper @Inject constructor() {

    fun map(model: Controller): ControllerItem = with(model) {
        ControllerItem(controllerType, id, name, isOnline)
    }
}
