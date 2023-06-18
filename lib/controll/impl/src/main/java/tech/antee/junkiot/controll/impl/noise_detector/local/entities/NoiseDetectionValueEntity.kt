package tech.antee.junkiot.controll.impl.noise_detector.local.entities

data class NoiseDetectionValueEntity(
    val id: Int,
    val controllerId: Int,
    val label: String,
    val timeStamp: Long
)
