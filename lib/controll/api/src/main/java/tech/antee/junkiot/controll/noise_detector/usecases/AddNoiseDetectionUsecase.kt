package tech.antee.junkiot.controll.noise_detector.usecases

import tech.antee.junkiot.controll.noise_detector.repositories.NoiseDetectorRepository
import javax.inject.Inject

class AddNoiseDetectionUsecase @Inject constructor(
    private val repository: NoiseDetectorRepository
) {

    suspend operator fun invoke(controllerId: Int, label: String) = repository.add(controllerId, label)
}
