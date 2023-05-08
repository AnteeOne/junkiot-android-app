package tech.antee.junkiot.simulator.light_sensor.impl.ui.items

sealed interface LightSensorUiState {
    object Empty : LightSensorUiState
    data class Value(val luxes: List<Float>) : LightSensorUiState
}
