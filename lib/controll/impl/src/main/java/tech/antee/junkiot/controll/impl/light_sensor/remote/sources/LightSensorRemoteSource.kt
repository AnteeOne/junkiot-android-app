package tech.antee.junkiot.controll.impl.light_sensor.remote.sources

import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.impl.light_sensor.remote.dto.AddLightSensorValueDto
import tech.antee.junkiot.controll.impl.light_sensor.remote.dto.LightPredictionDto

interface LightSensorRemoteSource {

    fun lightPredictions(controllerId: Int): Flow<List<LightPredictionDto>>

    fun closeLightPredictions(): Boolean

    suspend fun addLightSensorValue(dto: AddLightSensorValueDto)
}
