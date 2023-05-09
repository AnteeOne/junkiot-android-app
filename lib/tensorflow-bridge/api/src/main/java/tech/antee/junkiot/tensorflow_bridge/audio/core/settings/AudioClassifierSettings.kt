package tech.antee.junkiot.tensorflow_bridge.audio.core.settings

interface AudioClassifierSettings {
    val displayThreshold: Float
    val numOfResults: Int
    val classifyingThreadNum: Int
    val recordingThreadsNum: Int
    val recordingPeriodMs: Long
    val recordingDelayMs: Long
    val yamnetModelName: String
}
