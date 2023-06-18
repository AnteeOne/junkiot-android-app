package tech.antee.junkiot.controll.impl.noise_detector.remote.sources

import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.impl.noise_detector.remote.dto.AddNoiseDetectionValueDto
import tech.antee.junkiot.controll.impl.noise_detector.remote.dto.NoiseDetectionValueDto

interface NoiseDetectorRemoteSource {

    fun noiseDetections(controllerId: Int): Flow<List<NoiseDetectionValueDto>>

    fun closeNoiseDetections(): Boolean

    suspend fun addNoiseDetectionValue(dto: AddNoiseDetectionValueDto)
}
