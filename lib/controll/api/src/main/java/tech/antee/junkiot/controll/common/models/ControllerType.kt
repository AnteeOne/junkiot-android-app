package tech.antee.junkiot.controll.common.models

enum class ControllerType(val id: Int) {
    LIGHT_SENSOR(0)
}

fun Int.parseControllerType(): ControllerType = when (this) {
    ControllerType.LIGHT_SENSOR.id -> ControllerType.LIGHT_SENSOR
    else -> throw IllegalStateException("Unknown controller id!")
}
