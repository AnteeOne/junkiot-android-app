package tech.antee.junkiot.controll.impl.claps_detector.local.sources

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import tech.antee.junkiot.controll.impl.claps_detector.local.entities.ClapDetectionValueEntity
import tech.antee.junkiot.controll.impl.claps_detector.local.entities.ClapsDetectionsValuesStore
import tech.antee.junkiot.controll.impl.ktx.putAndCopy
import tech.antee.junkiot.controll.impl.ktx.putToListAndCopy
import javax.inject.Inject

class ClapsDetectorLocalSourceImpl @Inject constructor() : ClapsDetectorLocalSource {

    private val clapsDetectionsStore: MutableStateFlow<ClapsDetectionsValuesStore> = MutableStateFlow(HashMap())
    override fun clapsDetections(controllerId: Int): Flow<List<ClapDetectionValueEntity>> = clapsDetectionsStore
        .map { store -> store[controllerId] ?: emptyList() }

    override suspend fun add(controllerId: Int, entity: ClapDetectionValueEntity) {
        clapsDetectionsStore.value = clapsDetectionsStore.value.putToListAndCopy(controllerId, entity)
    }

    override suspend fun put(controllerId: Int, entities: List<ClapDetectionValueEntity>) {
        clapsDetectionsStore.value = clapsDetectionsStore.value.putAndCopy(controllerId, entities)
    }
}
