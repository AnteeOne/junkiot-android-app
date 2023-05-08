package tech.antee.junkiot.controll.light_sensor.usecases

import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.light_sensor.models.LightSensorPredictionValue
import tech.antee.junkiot.controll.light_sensor.repositories.LightSensorRepository
import javax.inject.Inject

class ObserveLightPredictionsUsecase @Inject constructor(
    private val repository: LightSensorRepository
) {

    operator fun invoke(controllerId: Int): Flow<List<LightSensorPredictionValue>> =
        repository.lightPredictions(controllerId)
}
