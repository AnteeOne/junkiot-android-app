package tech.antee.junkiot.controll.noise_detector.models

data class NoiseDetection(
    val id: Int,
    val controllerId: Int,
    val audioLabel: String,
    val timeStamp: Long
)
