package tech.antee.junkiot.simulator.light_sensor.ui

import androidx.navigation.NavType
import androidx.navigation.navArgument
import tech.antee.junkiot.multi_compose.ComposableFeature

abstract class LightSensorSimulatorFeature : ComposableFeature {

    protected val controllerIdArgument = "controllerId"
    private val controllerIdVariable = "controllerId"

    final override val featureRoute = "lightSensorSimulator?$controllerIdArgument={$controllerIdVariable}"

    final override val arguments = listOf(
        navArgument(controllerIdArgument) {
            type = NavType.IntType
        }
    )

    private fun buildRoute(id: Int) = "lightSensorSimulator?$controllerIdArgument=$id"

    fun destination(controllerId: Int) = buildRoute(controllerId)
}
