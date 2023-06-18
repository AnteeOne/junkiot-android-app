package tech.antee.junkiot.tensorflow_bridge.audio.services.noise

import kotlinx.coroutines.flow.Flow
import tech.antee.junkiot.tensorflow_bridge.audio.services.sound_detection.DetectorState

interface NoiseDetector {

    val detectorState: Flow<DetectorState>

    val noiseDetectionState: Flow<NoiseDetectionState>

    fun start()

    fun stop()

    fun toggle()
}
