package tech.antee.junkiot.controll.impl.common.remote.sources

import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.impl.common.remote.dto.AddControllerDto
import tech.antee.junkiot.controll.impl.common.remote.dto.ControllerDto
import tech.antee.junkiot.controll.impl.common.remote.services.ControllerApi
import tech.antee.junkiot.controll.impl.common.remote.services.ControllerReactiveApi
import javax.inject.Inject

class ControllerRemoteSourceImpl @Inject constructor(
    private val controllerApi: ControllerApi,
    private val controllerReactiveApi: ControllerReactiveApi
) : ControllerRemoteSource {

    override val controllers: Flow<List<ControllerDto>> = controllerReactiveApi.observeControllers()

    override suspend fun addController(addController: AddControllerDto): ControllerDto {
        return controllerApi.addController(addController)
    }

    override suspend fun deleteController(id: Int) {
        controllerApi.deleteController(id)
    }
}
