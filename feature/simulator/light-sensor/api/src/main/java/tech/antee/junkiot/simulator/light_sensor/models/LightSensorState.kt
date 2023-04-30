package tech.antee.junkiot.simulator.light_sensor.models

sealed interface LightSensorState {
    object Empty : LightSensorState
    data class Value(val lux: Float) : LightSensorState
}
