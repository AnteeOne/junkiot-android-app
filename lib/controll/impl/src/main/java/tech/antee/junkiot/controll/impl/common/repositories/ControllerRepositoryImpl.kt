package tech.antee.junkiot.controll.impl.common.repositories

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import tech.antee.junkiot.controll.common.models.AddController
import tech.antee.junkiot.controll.common.models.Controller
import tech.antee.junkiot.controll.common.repositories.ControllerRepository
import tech.antee.junkiot.controll.impl.common.local.sources.ControllerLocalSource
import tech.antee.junkiot.controll.impl.common.local.sources.SimulatorLocalSource
import tech.antee.junkiot.controll.impl.common.mappers.AddControllerDomainMapper
import tech.antee.junkiot.controll.impl.common.mappers.ControllerDomainMapper
import tech.antee.junkiot.controll.impl.common.mappers.ControllerSourceMapper
import tech.antee.junkiot.controll.impl.common.remote.sources.ControllerRemoteSource
import javax.inject.Inject

class ControllerRepositoryImpl @Inject constructor(
    private val controllerRemoteSource: ControllerRemoteSource,
    private val controllerLocalSource: ControllerLocalSource,
    private val simulatorLocalSource: SimulatorLocalSource
) : ControllerRepository {

    private val controllerDomainMapper by lazy { ControllerDomainMapper() }
    private val controllerSourceMapper by lazy { ControllerSourceMapper() }
    private val addControllerDomainMapper by lazy { AddControllerDomainMapper() }

    override val controllers: Flow<List<Controller>> = channelFlow {
        coroutineScope {
            launch {
                observeRemoteControllers()
            }
            launch {
                controllerLocalSource.controllers.map(controllerDomainMapper::map).collect { localControllers ->
                    send(localControllers)
                }
            }
        }
    }

    private suspend fun observeRemoteControllers() {
        controllerRemoteSource.controllers.collect { remoteControllers ->
            controllerLocalSource.update(remoteControllers.map(controllerSourceMapper::mapToEntity))
        }
    }

    override val simulators: Flow<List<Controller>> = simulatorLocalSource.simulators
        .map(controllerDomainMapper::map)

    override suspend fun addController(add: AddController): Controller = controllerRemoteSource
        .addController(addControllerDomainMapper.mapBack(add))
        .also { dto -> simulatorLocalSource.add(controllerSourceMapper.mapToEntity(dto)) }
        .let { dto -> controllerDomainMapper.map(dto) }

    override suspend fun deleteController(id: Int) {
        controllerRemoteSource.deleteController(id)
    }
}
