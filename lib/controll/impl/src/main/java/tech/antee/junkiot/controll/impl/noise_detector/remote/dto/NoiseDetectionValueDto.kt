package tech.antee.junkiot.controll.impl.noise_detector.remote.dto

data class NoiseDetectionValueDto(
    val id: Int,
    val controllerId: Int,
    val label: String,
    val timeStamp: Long
)
