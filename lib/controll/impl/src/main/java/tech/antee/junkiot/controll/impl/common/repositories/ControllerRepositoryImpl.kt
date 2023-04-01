package tech.antee.junkiot.controll.impl.common.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tech.antee.junkiot.controll.common.models.AddController
import tech.antee.junkiot.controll.common.models.Controller
import tech.antee.junkiot.controll.common.repositories.ControllerRepository
import tech.antee.junkiot.controll.impl.common.local.sources.ControllerLocalSource
import tech.antee.junkiot.controll.impl.common.mappers.AddControllerDomainMapper
import tech.antee.junkiot.controll.impl.common.mappers.ControllerDomainMapper
import tech.antee.junkiot.controll.impl.common.mappers.ControllerSourceMapper
import tech.antee.junkiot.controll.impl.common.network.sources.ControllerRemoteSource
import javax.inject.Inject

class ControllerRepositoryImpl @Inject constructor(
    private val controllerRemoteSource: ControllerRemoteSource,
    private val controllerLocalSource: ControllerLocalSource
) : ControllerRepository {

    private val controllerDomainMapper by lazy { ControllerDomainMapper() }
    private val controllerSourceMapper by lazy { ControllerSourceMapper() }
    private val addControllerDomainMapper by lazy { AddControllerDomainMapper() }

    override val controllers: Flow<List<Controller>> = controllerLocalSource.controllers
        .map(controllerDomainMapper::map)

    override suspend fun observeRemoteControllers() {
        controllerRemoteSource.controllers.collect { remoteControllers ->
            controllerLocalSource.update(remoteControllers.map(controllerSourceMapper::mapToEntity))
        }
    }

    override suspend fun addController(add: AddController): Controller = controllerRemoteSource
        .addController(addControllerDomainMapper.mapBack(add))
        .let { dto -> controllerDomainMapper.map(dto) }

    override suspend fun deleteController(id: Int) {
        controllerRemoteSource.deleteController(id)
    }
}
