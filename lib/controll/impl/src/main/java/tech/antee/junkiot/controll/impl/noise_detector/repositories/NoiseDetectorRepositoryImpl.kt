package tech.antee.junkiot.controll.impl.noise_detector.repositories

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import tech.antee.junkiot.controll.noise_detector.models.NoiseDetection
import tech.antee.junkiot.controll.noise_detector.repositories.NoiseDetectorRepository
import tech.antee.junkiot.controll.impl.noise_detector.local.sources.NoiseDetectorLocalSource
import tech.antee.junkiot.controll.impl.noise_detector.mappers.NoiseDetectorDomainMapper
import tech.antee.junkiot.controll.impl.noise_detector.remote.sources.NoiseDetectorRemoteSource
import javax.inject.Inject

class NoiseDetectorRepositoryImpl @Inject constructor(
    private val noiseDetectorLocalSource: NoiseDetectorLocalSource,
    private val noiseDetectorRemoteSource: NoiseDetectorRemoteSource
) : NoiseDetectorRepository {

    private val mapper by lazy { NoiseDetectorDomainMapper() }

    override fun noiseDetections(controllerId: Int): Flow<List<NoiseDetection>> = channelFlow {
        coroutineScope {
            launch {
                observeRemoteNoiseDetections(controllerId)
            }
            launch {
                noiseDetectorLocalSource
                    .noiseDetections(controllerId)
                    .map { entities -> entities.map(mapper::map) }
                    .collect { send(it) }
            }
        }
    }
        .buffer(Channel.UNLIMITED)
        .onCompletion { noiseDetectorRemoteSource.closeNoiseDetections() }

    private suspend fun observeRemoteNoiseDetections(controllerId: Int) {
        noiseDetectorRemoteSource
            .noiseDetections(controllerId)
            .map { dtos -> dtos.map(mapper::map).map(mapper::mapBack) }
            .collect { entities ->
                noiseDetectorLocalSource.put(controllerId, entities)
            }
    }

    override suspend fun add(controllerId: Int, label: String) {
        noiseDetectorRemoteSource.addNoiseDetectionValue(mapper.mapBack(controllerId, label))
    }
}
