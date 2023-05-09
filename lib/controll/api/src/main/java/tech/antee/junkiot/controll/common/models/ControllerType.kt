package tech.antee.junkiot.controll.common.models

enum class ControllerType(val id: Int) {
    LightSensor(0),
    ClapsDetector(1),
    NoiseSensor(2)
}

fun Int.parseControllerType(): ControllerType = when (this) {
    ControllerType.LightSensor.id -> ControllerType.LightSensor
    ControllerType.ClapsDetector.id -> ControllerType.ClapsDetector
    ControllerType.NoiseSensor.id -> ControllerType.NoiseSensor
    else -> throw IllegalStateException("Unknown controller id!")
}
