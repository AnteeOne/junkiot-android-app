package tech.antee.junkiot.controll.common.usecases

import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.common.models.Controller
import tech.antee.junkiot.controll.common.repositories.ControllerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ObserveControllersUsecase @Inject constructor(
    private val repository: ControllerRepository
) {

    operator fun invoke(): Flow<List<Controller>> = repository.controllers
}
