package tech.antee.junkiot.controll.impl.claps_detector.repositories

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import tech.antee.junkiot.controll.claps_detector.models.ClapDetection
import tech.antee.junkiot.controll.claps_detector.repositories.ClapsDetectorRepository
import tech.antee.junkiot.controll.impl.claps_detector.local.sources.ClapsDetectorLocalSource
import tech.antee.junkiot.controll.impl.claps_detector.mappers.ClapsDetectorDomainMapper
import tech.antee.junkiot.controll.impl.claps_detector.remote.sources.ClapsDetectorRemoteSource
import javax.inject.Inject

class ClapsDetectorRepositoryImpl @Inject constructor(
    private val clapsDetectorLocalSource: ClapsDetectorLocalSource,
    private val clapsDetectorRemoteSource: ClapsDetectorRemoteSource
) : ClapsDetectorRepository {

    private val mapper by lazy { ClapsDetectorDomainMapper() }

    override fun clapsDetections(controllerId: Int): Flow<List<ClapDetection>> = channelFlow {
        coroutineScope {
            launch {
                observeRemoteClapDetections(controllerId)
            }
            launch {
                clapsDetectorLocalSource
                    .clapsDetections(controllerId)
                    .map { entities -> entities.map(mapper::map) }
                    .collect { send(it) }
            }
        }
    }
        .buffer(Channel.UNLIMITED)
        .onCompletion { clapsDetectorRemoteSource.closeClapDetections() }

    private suspend fun observeRemoteClapDetections(controllerId: Int) {
        clapsDetectorRemoteSource
            .clapDetections(controllerId)
            .map { dtos -> dtos.map(mapper::map).map(mapper::mapBack) }
            .collect { entities ->
                clapsDetectorLocalSource.put(controllerId, entities)
            }
    }

    override suspend fun add(controllerId: Int) {
        clapsDetectorRemoteSource.addClapDetectionValue(mapper.mapBack(controllerId))
    }
}
