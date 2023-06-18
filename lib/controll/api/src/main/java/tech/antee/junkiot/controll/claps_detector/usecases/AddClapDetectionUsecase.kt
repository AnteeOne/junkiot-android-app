package tech.antee.junkiot.controll.claps_detector.usecases

import tech.antee.junkiot.controll.claps_detector.repositories.ClapsDetectorRepository
import javax.inject.Inject

class AddClapDetectionUsecase @Inject constructor(
    private val repository: ClapsDetectorRepository
) {

    suspend operator fun invoke(controllerId: Int) = repository.add(controllerId)
}
