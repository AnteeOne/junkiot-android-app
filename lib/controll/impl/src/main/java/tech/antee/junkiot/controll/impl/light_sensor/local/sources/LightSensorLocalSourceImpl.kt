package tech.antee.junkiot.controll.impl.light_sensor.local.sources

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import tech.antee.junkiot.controll.impl.ktx.putAndCopy
import tech.antee.junkiot.controll.impl.ktx.putToListAndCopy
import tech.antee.junkiot.controll.impl.light_sensor.local.entities.LightPredictionEntity
import tech.antee.junkiot.controll.impl.light_sensor.local.entities.LightPredictionsStore
import tech.antee.junkiot.controll.impl.light_sensor.local.entities.LuxEntity
import tech.antee.junkiot.controll.impl.light_sensor.local.entities.LuxValuesStore
import javax.inject.Inject

class LightSensorLocalSourceImpl @Inject constructor() : LightSensorLocalSource {

    private val luxValuesStore: MutableStateFlow<LuxValuesStore> = MutableStateFlow(HashMap())
    override fun luxValues(controllerId: Int): Flow<List<LuxEntity>> = luxValuesStore
        .map { store -> store[controllerId] ?: listOf() }

    override suspend fun add(controllerId: Int, entity: LuxEntity) {
        luxValuesStore.value = luxValuesStore.value.putToListAndCopy(controllerId, entity)
    }

    private val lightPredictionsStore: MutableStateFlow<LightPredictionsStore> = MutableStateFlow(HashMap())
    override fun lightPredictions(controllerId: Int): Flow<List<LightPredictionEntity>> = lightPredictionsStore
        .map { store -> store[controllerId] ?: listOf() }

    override suspend fun add(controllerId: Int, entity: LightPredictionEntity) {
        lightPredictionsStore.value = lightPredictionsStore.value.putToListAndCopy(controllerId, entity)
    }

    override suspend fun put(controllerId: Int, entities: List<LightPredictionEntity>) {
        lightPredictionsStore.value = lightPredictionsStore.value.putAndCopy(controllerId, entities)
    }
}
