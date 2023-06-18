package tech.antee.junkiot.controll.impl.claps_detector.local.sources

import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.impl.claps_detector.local.entities.ClapDetectionValueEntity

interface ClapsDetectorLocalSource {

    fun clapsDetections(controllerId: Int): Flow<List<ClapDetectionValueEntity>>

    suspend fun add(controllerId: Int, entity: ClapDetectionValueEntity)

    suspend fun put(controllerId: Int, entities: List<ClapDetectionValueEntity>)
}
