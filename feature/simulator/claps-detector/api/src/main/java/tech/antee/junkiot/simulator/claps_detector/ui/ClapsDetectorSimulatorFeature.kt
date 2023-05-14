package tech.antee.junkiot.simulator.claps_detector.ui

import androidx.navigation.NavType
import androidx.navigation.navArgument
import tech.antee.junkiot.multi_compose.ComposableFeature

abstract class ClapsDetectorSimulatorFeature : ComposableFeature {

    protected val controllerIdArgument = "controllerId"
    private val controllerIdVariable = "controllerId"

    final override val featureRoute = "clapsDetectorSimulator?$controllerIdArgument={$controllerIdVariable}"

    final override val arguments = listOf(
        navArgument(controllerIdArgument) {
            type = NavType.IntType
        }
    )

    private fun buildRoute(id: Int) = "clapsDetectorSimulator?$controllerIdArgument=$id"

    fun destination(controllerId: Int) = buildRoute(controllerId)
}
