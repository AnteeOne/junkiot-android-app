package tech.antee.junkiot.controll.impl.claps_detector.remote.sources

import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.impl.claps_detector.remote.dto.AddClapDetectionValueDto
import tech.antee.junkiot.controll.impl.claps_detector.remote.dto.ClapDetectionValueDto

interface ClapsDetectorRemoteSource {

    fun clapDetections(controllerId: Int): Flow<List<ClapDetectionValueDto>>

    fun closeClapDetections(): Boolean

    suspend fun addClapDetectionValue(dto: AddClapDetectionValueDto)
}
