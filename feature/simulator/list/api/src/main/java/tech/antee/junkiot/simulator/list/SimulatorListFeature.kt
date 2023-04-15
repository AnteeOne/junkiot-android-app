package tech.antee.junkiot.simulator.list

import tech.antee.junkiot.multi_compose.ComposableFeature

abstract class SimulatorListFeature : ComposableFeature {

    override val featureRoute: String = "simulator_list"
}
