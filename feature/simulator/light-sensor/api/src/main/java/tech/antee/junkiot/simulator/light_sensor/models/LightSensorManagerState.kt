package tech.antee.junkiot.simulator.light_sensor.models

sealed interface LightSensorManagerState {
    object Enabled : LightSensorManagerState
    object Disabled : LightSensorManagerState
}
