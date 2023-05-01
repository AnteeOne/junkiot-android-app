package tech.antee.junkiot.simulator.light_sensor.impl.ui.items

import tech.antee.junkiot.simulator.light_sensor.models.LightSensorManagerState

data class UiState(
    val simulator: SimulatorItem?,
    val lightSensorState: LightSensorUiState?,
    val lightPredictionsState: LightPredictionUiState,
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

    fun withLightSensorState(lightSensorState: LightSensorUiState) = copy(
        lightSensorState = lightSensorState
    )

    fun withLightPredictionState(lightPredictionsState: LightPredictionUiState) = copy(
        lightPredictionsState = lightPredictionsState
    )

    fun withLightSensorManagerState(lightSensorManagerState: LightSensorManagerState) = copy(
        sensorManagerState = lightSensorManagerState
    )

    fun withError() = copy(isError = true)

    companion object {

        fun empty(): UiState = UiState(
            simulator = null,
            lightSensorState = null,
            lightPredictionsState = LightPredictionUiState.Empty,
            sensorManagerState = null,
            isLoading = false,
            isError = false
        )
    }
}
