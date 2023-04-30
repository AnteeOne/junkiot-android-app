package tech.antee.junkiot.controll.impl.light_sensor.remote.sources

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import tech.antee.junkiot.controll.impl.light_sensor.remote.dto.AddLightSensorValueDto
import tech.antee.junkiot.controll.impl.light_sensor.remote.dto.LightPredictionDto
import tech.antee.junkiot.controll.impl.light_sensor.remote.services.LightSensorApi
import tech.antee.junkiot.controll.impl.light_sensor.remote.services.LightSensorReactiveApi
import javax.inject.Inject

class LightSensorRemoteSourceImpl @Inject constructor(
    private val api: LightSensorApi,
    private val reactiveApi: LightSensorReactiveApi
) : LightSensorRemoteSource {

    override fun lightPredictions(controllerId: Int): Flow<List<LightPredictionDto>> =
        reactiveApi.observeLightSensorPredictions()
            .onStart { reactiveApi.sendControllerId(controllerId) }

    override fun closeLightPredictions(): Boolean = reactiveApi.sendControllerId(-1)

    override suspend fun addLightSensorValue(dto: AddLightSensorValueDto) = api.addLightSensorValue(dto)
}
