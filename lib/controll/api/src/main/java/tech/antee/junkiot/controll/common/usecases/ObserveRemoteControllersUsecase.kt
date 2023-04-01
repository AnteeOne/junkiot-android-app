package tech.antee.junkiot.controll.common.usecases

import tech.antee.junkiot.controll.common.repositories.ControllerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ObserveRemoteControllersUsecase @Inject constructor(
    private val repository: ControllerRepository
) {

    suspend operator fun invoke(): Unit = repository.observeRemoteControllers()
}
