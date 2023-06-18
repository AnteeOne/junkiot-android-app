package tech.antee.junkiot.simulator.claps_detector.impl.ui.items

sealed interface DetectorUiState {
    object Empty : DetectorUiState
    object Enabled : DetectorUiState
    object Disabled : DetectorUiState
}
