package tech.antee.junkiot.controll.light_sensor.usecases

import tech.antee.junkiot.controll.light_sensor.models.Lux
import tech.antee.junkiot.controll.light_sensor.repositories.LightSensorRepository
import javax.inject.Inject

class AddLightSensorValueUsecase @Inject constructor(
    private val repository: LightSensorRepository
) {

    suspend operator fun invoke(controllerId: Int, lux: Lux) = repository.add(controllerId, lux)
}
