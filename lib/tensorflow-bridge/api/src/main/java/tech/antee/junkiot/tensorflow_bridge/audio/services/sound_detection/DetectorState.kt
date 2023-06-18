package tech.antee.junkiot.tensorflow_bridge.audio.services.sound_detection

sealed interface DetectorState {
    object Empty : DetectorState
    object Enabled : DetectorState
    object Disabled : DetectorState
}
