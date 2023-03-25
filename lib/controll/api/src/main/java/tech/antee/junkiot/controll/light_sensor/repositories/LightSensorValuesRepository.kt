package tech.antee.junkiot.controll.light_sensor.repositories

import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.light_sensor.models.LightPrediction
import tech.antee.junkiot.controll.light_sensor.models.Lux

interface LightSensorValuesRepository {

    fun luxValues(controllerId: Int): Flow<List<Lux>>

    fun lightPredictions(controllerId: Int): Flow<List<LightPrediction>>

    suspend fun add(controllerId: Int, luxValue: Lux)
}
