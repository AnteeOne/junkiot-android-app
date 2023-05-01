package tech.antee.junkiot.controll.impl.light_sensor.repositories

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import tech.antee.junkiot.controll.impl.light_sensor.local.sources.LightSensorLocalSource
import tech.antee.junkiot.controll.impl.light_sensor.mappers.LightSensorDomainMapper
import tech.antee.junkiot.controll.impl.light_sensor.remote.sources.LightSensorRemoteSource
import tech.antee.junkiot.controll.light_sensor.models.LightSensorPredictionValue
import tech.antee.junkiot.controll.light_sensor.models.Lux
import tech.antee.junkiot.controll.light_sensor.repositories.LightSensorRepository
import javax.inject.Inject

@FlowPreview
class LightSensorRepositoryImpl @Inject constructor(
    private val lightSensorLocalSource: LightSensorLocalSource,
    private val lightSensorRemoteSource: LightSensorRemoteSource
) : LightSensorRepository {

    private val mapper by lazy { LightSensorDomainMapper() }

    override fun luxValues(controllerId: Int): Flow<List<Lux>> = lightSensorLocalSource
        .luxValues(controllerId)
        .map { entities -> entities.map(mapper::map) }

    override fun lightPredictions(controllerId: Int): Flow<List<LightSensorPredictionValue>> = channelFlow {
        coroutineScope {
            launch {
                observeRemoteLightPredictions(controllerId)
            }
            launch {
                lightSensorLocalSource
                    .lightPredictions(controllerId)
                    .map { entities -> entities.map(mapper::map) }
                    .collect {
                        send(it)
                    }
            }
        }
    }
        .buffer(Channel.UNLIMITED)
        .onCompletion { lightSensorRemoteSource.closeLightPredictions() }

    private suspend fun observeRemoteLightPredictions(controllerId: Int) {
        lightSensorRemoteSource
            .lightPredictions(controllerId)
            .map { entities -> entities.map(mapper::map).map(mapper::mapBack) }
            .collect { values ->
                lightSensorLocalSource.put(controllerId, values)
            }
    }

    override suspend fun add(controllerId: Int, luxValue: Lux) {
        lightSensorLocalSource.add(controllerId, mapper.mapBack(luxValue))
        lightSensorRemoteSource.addLightSensorValue(mapper.mapBack(luxValue, controllerId))
    }
}
