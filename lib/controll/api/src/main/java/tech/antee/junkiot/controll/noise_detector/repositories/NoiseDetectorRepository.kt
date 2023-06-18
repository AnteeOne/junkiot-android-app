package tech.antee.junkiot.controll.noise_detector.repositories

import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.noise_detector.models.NoiseDetection

interface NoiseDetectorRepository {

    fun noiseDetections(controllerId: Int): Flow<List<NoiseDetection>>

    suspend fun add(controllerId: Int, label: String)
}
