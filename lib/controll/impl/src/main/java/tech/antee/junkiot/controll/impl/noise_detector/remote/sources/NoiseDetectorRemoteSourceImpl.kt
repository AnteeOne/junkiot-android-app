package tech.antee.junkiot.controll.impl.noise_detector.remote.sources

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import tech.antee.junkiot.controll.impl.noise_detector.remote.dto.AddNoiseDetectionValueDto
import tech.antee.junkiot.controll.impl.noise_detector.remote.dto.NoiseDetectionValueDto
import tech.antee.junkiot.controll.impl.noise_detector.remote.services.NoiseDetectorApi
import tech.antee.junkiot.controll.impl.noise_detector.remote.services.NoiseDetectorReactiveApi
import javax.inject.Inject

class NoiseDetectorRemoteSourceImpl @Inject constructor(
    private val api: NoiseDetectorApi,
    private val reactiveApi: NoiseDetectorReactiveApi
) : NoiseDetectorRemoteSource {

    override fun noiseDetections(controllerId: Int): Flow<List<NoiseDetectionValueDto>> = reactiveApi
        .observeNoiseDetections()
        .onStart { reactiveApi.sendControllerId(controllerId) }

    override fun closeNoiseDetections(): Boolean = reactiveApi.sendControllerId(-1)

    override suspend fun addNoiseDetectionValue(dto: AddNoiseDetectionValueDto) = api.addNoiseDetectionValue(dto)
}
