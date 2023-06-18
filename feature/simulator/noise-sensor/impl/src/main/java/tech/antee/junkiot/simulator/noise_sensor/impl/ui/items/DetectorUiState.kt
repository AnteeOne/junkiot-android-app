package tech.antee.junkiot.simulator.noise_sensor.impl.ui.items

sealed interface DetectorUiState {
    object Empty : DetectorUiState
    object Enabled : DetectorUiState
    object Disabled : DetectorUiState
}
