package tech.antee.junkiot.controll.noise_detector.usecases

import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.noise_detector.models.NoiseDetection
import tech.antee.junkiot.controll.noise_detector.repositories.NoiseDetectorRepository
import javax.inject.Inject

class ObserveNoiseDetectionsUsecase @Inject constructor(
    private val repository: NoiseDetectorRepository
) {

    operator fun invoke(controllerId: Int): Flow<List<NoiseDetection>> =
        repository.noiseDetections(controllerId)
}
