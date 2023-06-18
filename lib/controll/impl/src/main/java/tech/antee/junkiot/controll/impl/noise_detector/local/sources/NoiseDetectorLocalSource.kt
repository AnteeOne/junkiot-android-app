package tech.antee.junkiot.controll.impl.noise_detector.local.sources

import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.impl.noise_detector.local.entities.NoiseDetectionValueEntity

interface NoiseDetectorLocalSource {

    fun noiseDetections(controllerId: Int): Flow<List<NoiseDetectionValueEntity>>

    suspend fun add(controllerId: Int, entity: NoiseDetectionValueEntity)

    suspend fun put(controllerId: Int, entities: List<NoiseDetectionValueEntity>)
}
