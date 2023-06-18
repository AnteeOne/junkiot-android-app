package tech.antee.junkiot.simulator.noise_sensor.impl.ui.items

import tech.antee.junkiot.tensorflow_bridge.audio.core.models.AudioLabel

sealed interface NoiseDetectionUiState {
    object Empty : NoiseDetectionUiState
    data class Noise(val label: AudioLabel) : NoiseDetectionUiState
}
