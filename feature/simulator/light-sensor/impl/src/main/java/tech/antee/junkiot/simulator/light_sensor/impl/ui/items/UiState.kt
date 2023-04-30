package tech.antee.junkiot.simulator.light_sensor.impl.ui.items

import tech.antee.junkiot.simulator.light_sensor.models.LightSensorManagerState
import tech.antee.junkiot.simulator.light_sensor.models.LightSensorState

data class UiState(
    val simulator: SimulatorItem?,
    val lightSensorState: LightSensorState?,
    val sensorManagerState: LightSensorManagerState?,
    val isLoading: Boolean,
    val isError: Boolean
) {

    fun withLoading(isLoading: Boolean) = copy(isLoading = isLoading)

    fun withSimulatorItem(simulatorItem: SimulatorItem) = copy(
        simulator = simulatorItem,
        isLoading = false,
        isError = false
    )

    fun withLightSensorState(lightSensorState: LightSensorState) = copy(
        lightSensorState = lightSensorState
    )

    fun withLightSensorManagerState(lightSensorManagerState: LightSensorManagerState) = copy(
        sensorManagerState = lightSensorManagerState
    )

    fun withError() = copy(isError = true)

    companion object {

        fun empty(): UiState = UiState(
            simulator = null,
            lightSensorState = null,
            sensorManagerState = null,
            isLoading = false,
            isError = false
        )
    }
}
