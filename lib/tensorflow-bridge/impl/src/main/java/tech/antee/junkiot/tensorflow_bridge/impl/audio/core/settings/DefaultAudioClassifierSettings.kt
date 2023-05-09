package tech.antee.junkiot.tensorflow_bridge.impl.audio.core.settings

import tech.antee.junkiot.tensorflow_bridge.audio.core.settings.AudioClassifierSettings

object DefaultAudioClassifierSettings : AudioClassifierSettings {
    override val displayThreshold = 0.3f
    override val numOfResults = 1
    override val classifyingThreadNum = 2
    override val recordingThreadsNum = 1
    override val recordingPeriodMs = 1_000L
    override val recordingDelayMs = 0L
    override val yamnetModelName = "yamnet.tflite"
}
