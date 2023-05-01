package tech.antee.junkiot.controll.impl.light_sensor.local.sources

import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.impl.light_sensor.local.entities.LightPredictionEntity
import tech.antee.junkiot.controll.impl.light_sensor.local.entities.LuxEntity

interface LightSensorLocalSource {

    fun luxValues(controllerId: Int): Flow<List<LuxEntity>>

    suspend fun add(controllerId: Int, entity: LuxEntity)

    fun lightPredictions(controllerId: Int): Flow<List<LightPredictionEntity>>

    suspend fun add(controllerId: Int, entity: LightPredictionEntity)

    suspend fun put(controllerId: Int, entities: List<LightPredictionEntity>)
}
