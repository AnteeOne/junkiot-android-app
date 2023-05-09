package tech.antee.junkiot.controll.common.models

enum class ControllerType(val id: Int) {
    LightSensor(0)
}

fun Int.parseControllerType(): ControllerType = when (this) {
    ControllerType.LightSensor.id -> ControllerType.LightSensor
    else -> throw IllegalStateException("Unknown controller id!")
}
