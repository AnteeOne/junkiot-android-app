package tech.antee.junkiot.controll.claps_detector.usecases

import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.claps_detector.models.ClapDetection
import tech.antee.junkiot.controll.claps_detector.repositories.ClapsDetectorRepository
import javax.inject.Inject

class ObserveClapDetectionsUsecase @Inject constructor(
    private val repository: ClapsDetectorRepository
) {

    operator fun invoke(controllerId: Int): Flow<List<ClapDetection>> =
        repository.clapsDetections(controllerId)
}
