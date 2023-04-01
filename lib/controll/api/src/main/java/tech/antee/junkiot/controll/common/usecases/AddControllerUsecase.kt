package tech.antee.junkiot.controll.common.usecases

import tech.antee.junkiot.controll.common.models.AddController
import tech.antee.junkiot.controll.common.models.Controller
import tech.antee.junkiot.controll.common.repositories.ControllerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddControllerUsecase @Inject constructor(
    private val repository: ControllerRepository
) {

    suspend operator fun invoke(addController: AddController): Controller = repository.addController(addController)
}
