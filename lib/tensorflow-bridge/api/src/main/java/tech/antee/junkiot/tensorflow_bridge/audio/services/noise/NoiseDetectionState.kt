package tech.antee.junkiot.tensorflow_bridge.audio.services.noise

import tech.antee.junkiot.tensorflow_bridge.audio.core.models.AudioLabel

sealed interface NoiseDetectionState {
    object Empty : NoiseDetectionState
    data class Noise(val label: AudioLabel) : NoiseDetectionState
}
