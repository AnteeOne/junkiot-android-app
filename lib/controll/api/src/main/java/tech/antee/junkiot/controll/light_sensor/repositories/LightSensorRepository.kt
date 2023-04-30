package tech.antee.junkiot.controll.light_sensor.repositories

import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.light_sensor.models.LightSensorPredictionValue
import tech.antee.junkiot.controll.light_sensor.models.Lux

interface LightSensorRepository {

    fun luxValues(controllerId: Int): Flow<List<Lux>>

    fun lightPredictions(controllerId: Int): Flow<List<LightSensorPredictionValue>>

    suspend fun add(controllerId: Int, luxValue: Lux)
}
