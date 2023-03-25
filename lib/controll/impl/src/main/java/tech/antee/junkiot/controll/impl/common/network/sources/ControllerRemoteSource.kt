package tech.antee.junkiot.controll.impl.common.network.sources

import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.impl.common.network.dto.AddControllerDto
import tech.antee.junkiot.controll.impl.common.network.dto.ControllerDto

interface ControllerRemoteSource {

    val controllers: Flow<List<ControllerDto>>

    suspend fun addController(addController: AddControllerDto): ControllerDto

    suspend fun deleteController(id: Int)
}
