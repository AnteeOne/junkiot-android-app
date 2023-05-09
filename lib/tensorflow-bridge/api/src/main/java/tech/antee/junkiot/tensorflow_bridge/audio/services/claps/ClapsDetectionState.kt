package tech.antee.junkiot.tensorflow_bridge.audio.services.claps

sealed interface ClapsDetectionState {
    object Empty : ClapsDetectionState
    object Clap : ClapsDetectionState
}
