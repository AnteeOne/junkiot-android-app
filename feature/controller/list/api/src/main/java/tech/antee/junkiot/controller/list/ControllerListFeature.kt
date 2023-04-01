package tech.antee.junkiot.controller.list

import tech.antee.junkiot.multi_compose.ComposableFeature

abstract class ControllerListFeature : ComposableFeature {

    override val featureRoute: String = "controller_list"
}
