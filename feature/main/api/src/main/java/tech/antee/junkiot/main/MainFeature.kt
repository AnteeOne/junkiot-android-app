package tech.antee.junkiot.main

import tech.antee.junkiot.multi_compose.ComposableFeature

abstract class MainFeature : ComposableFeature {

    final override val featureRoute = "main"

    fun destination() = featureRoute
}
