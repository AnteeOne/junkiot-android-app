package tech.antee.junkiot.tensorflow_bridge.audio.services.claps

import kotlinx.coroutines.flow.Flow

interface ClapsDetector {

    val clapsDetectionState: Flow<ClapsDetectionState>

    fun start()

    fun stop()
}
