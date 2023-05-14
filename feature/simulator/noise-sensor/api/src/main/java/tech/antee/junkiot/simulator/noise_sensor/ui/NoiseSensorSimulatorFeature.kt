package tech.antee.junkiot.simulator.noise_sensor.ui

import androidx.navigation.NavType
import androidx.navigation.navArgument
import tech.antee.junkiot.multi_compose.ComposableFeature

abstract class NoiseSensorSimulatorFeature : ComposableFeature {

    protected val controllerIdArgument = "controllerId"
    private val controllerIdVariable = "controllerId"

    final override val featureRoute = "noiseSensorSimulator?$controllerIdArgument={$controllerIdVariable}"

    final override val arguments = listOf(
        navArgument(controllerIdArgument) {
            type = NavType.IntType
        }
    )

    private fun buildRoute(id: Int) = "noiseSensorSimulator?$controllerIdArgument=$id"

    fun destination(controllerId: Int) = buildRoute(controllerId)
}
