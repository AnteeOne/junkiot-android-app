package tech.antee.junkiot.controll.claps_detector.repositories

import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.controll.claps_detector.models.ClapDetection

interface ClapsDetectorRepository {

    fun clapsDetections(controllerId: Int): Flow<List<ClapDetection>>

    suspend fun add(controllerId: Int)
}
