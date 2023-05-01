package tech.antee.junkiot.controll.common.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tech.antee.junkiot.controll.common.models.Controller
import tech.antee.junkiot.controll.common.repositories.ControllerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetReactiveSimulatorUsecase @Inject constructor(
    private val repository: ControllerRepository
) {

    operator fun invoke(controllerId: Int): Flow<Controller> = repository
        .simulators
        .map { list -> list.first { it.id == controllerId } }
}
