package tech.antee.junkiot.controll.impl.common.remote.sources

import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.impl.common.remote.dto.AddControllerDto
import tech.antee.junkiot.controll.impl.common.remote.dto.ControllerDto

interface ControllerRemoteSource {

    val controllers: Flow<List<ControllerDto>>

    suspend fun addController(addController: AddControllerDto): ControllerDto

    suspend fun deleteController(id: Int)
}
