package tech.antee.junkiot.controll.impl.noise_detector.local.sources

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import tech.antee.junkiot.controll.impl.ktx.putAndCopy
import tech.antee.junkiot.controll.impl.ktx.putToListAndCopy
import tech.antee.junkiot.controll.impl.noise_detector.local.entities.NoiseDetectionValueEntity
import tech.antee.junkiot.controll.impl.noise_detector.local.entities.NoiseDetectionsValuesStore
import javax.inject.Inject

class NoiseDetectorLocalSourceImpl @Inject constructor() : NoiseDetectorLocalSource {

    private val noiseDetectionsStore: MutableStateFlow<NoiseDetectionsValuesStore> = MutableStateFlow(HashMap())
    override fun noiseDetections(controllerId: Int): Flow<List<NoiseDetectionValueEntity>> = noiseDetectionsStore
        .map { store -> store[controllerId] ?: emptyList() }

    override suspend fun add(controllerId: Int, entity: NoiseDetectionValueEntity) {
        noiseDetectionsStore.value = noiseDetectionsStore.value.putToListAndCopy(controllerId, entity)
    }

    override suspend fun put(controllerId: Int, entities: List<NoiseDetectionValueEntity>) {
        noiseDetectionsStore.value = noiseDetectionsStore.value.putAndCopy(controllerId, entities)
    }
}
