package tech.antee.junkiot.controll.impl.claps_detector.remote.sources

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import tech.antee.junkiot.controll.impl.claps_detector.remote.dto.AddClapDetectionValueDto
import tech.antee.junkiot.controll.impl.claps_detector.remote.dto.ClapDetectionValueDto
import tech.antee.junkiot.controll.impl.claps_detector.remote.services.ClapsDetectorApi
import tech.antee.junkiot.controll.impl.claps_detector.remote.services.ClapsDetectorReactiveApi
import javax.inject.Inject

class ClapsDetectorRemoteSourceImpl @Inject constructor(
    private val api: ClapsDetectorApi,
    private val reactiveApi: ClapsDetectorReactiveApi
) : ClapsDetectorRemoteSource {

    override fun clapDetections(controllerId: Int): Flow<List<ClapDetectionValueDto>> = reactiveApi
        .observeClapsDetections()
        .onStart { reactiveApi.sendControllerId(controllerId) }

    override fun closeClapDetections(): Boolean = reactiveApi.sendControllerId(-1)

    override suspend fun addClapDetectionValue(dto: AddClapDetectionValueDto) = api.addClapDetectionValue(dto)
}
