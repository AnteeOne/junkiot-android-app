package tech.antee.junkiot.simulator.light_sensor.managers

import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.simulator.light_sensor.models.LightSensorManagerState
import tech.antee.junkiot.simulator.light_sensor.models.LightSensorState

interface LightSensorManager {

    val lightSensorManagerState: Flow<LightSensorManagerState>

    val lightSensorValues: Flow<LightSensorState>

    interface Settings {

        val valuesDebounceMs: Long

        val valuesCacheSize: Int
    }
}
