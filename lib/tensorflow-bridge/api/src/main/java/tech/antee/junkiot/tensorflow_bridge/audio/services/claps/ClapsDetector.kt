package tech.antee.junkiot.tensorflow_bridge.audio.services.claps

import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.tensorflow_bridge.audio.services.sound_detection.DetectorState

interface ClapsDetector {

    val detectorState: Flow<DetectorState>

    val clapsDetectionState: Flow<ClapsDetectionState>

    fun start()

    fun stop()

    fun toggle()
}
