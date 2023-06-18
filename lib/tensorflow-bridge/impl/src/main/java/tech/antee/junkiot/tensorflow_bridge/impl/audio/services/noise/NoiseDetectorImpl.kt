package tech.antee.junkiot.tensorflow_bridge.impl.audio.services.noise

import android.content.Context
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import tech.antee.junkiot.tensorflow_bridge.audio.core.models.AudioLabel
import tech.antee.junkiot.tensorflow_bridge.audio.services.noise.NoiseDetectionState
import tech.antee.junkiot.tensorflow_bridge.audio.services.noise.NoiseDetector
import tech.antee.junkiot.tensorflow_bridge.audio.services.noise.NoiseDetectorSettings
import tech.antee.junkiot.tensorflow_bridge.audio.services.sound_detection.DetectorState
import tech.antee.junkiot.tensorflow_bridge.impl.audio.core.delegate.AudioClassifierDelegate
import javax.inject.Inject

@FlowPreview
class NoiseDetectorImpl @Inject constructor(
    context: Context,
    settings: NoiseDetectorSettings
) : NoiseDetector, AudioClassifierDelegate(context, Meta.audioLabels) {

    private val _detectorState: MutableStateFlow<DetectorState> =
        MutableStateFlow(DetectorState.Empty)
    override val detectorState: Flow<DetectorState> = _detectorState

    private val _noiseDetectionState: MutableStateFlow<NoiseDetectionState> =
        MutableStateFlow(NoiseDetectionState.Empty)
    override val noiseDetectionState: Flow<NoiseDetectionState> = _noiseDetectionState
        .debounce(settings.debounceMs)

    override val onInitialized = {
        _detectorState.value = DetectorState.Disabled
    }

    override fun classifyAudio() {
        tensorAudio?.load(recorder)
        val output = audioClassifier?.classify(tensorAudio)
        val supportedLabels = AudioLabel
            .values()
            .toList()
            .map { label -> label.labelValue }
        val label = output
            ?.getOrNull(0)
            ?.categories
            ?.getOrNull(0)
            ?.label
        when {
            label in supportedLabels && label != null -> _noiseDetectionState.value = NoiseDetectionState.Noise(
                AudioLabel.valueOf(label)
            )
            else -> _noiseDetectionState.value = NoiseDetectionState.Empty
        }
    }

    override fun start() {
        _detectorState.value = DetectorState.Enabled
        startAudioClassification()
    }

    override fun stop() {
        _detectorState.value = DetectorState.Disabled
        stopAudioClassification()
    }

    override fun toggle() {
        when (_detectorState.value) {
            DetectorState.Disabled -> start()
            DetectorState.Enabled -> stop()
            DetectorState.Empty -> {}
        }
    }
}

private object Meta {
    val audioLabels = emptyList<AudioLabel>()
}
