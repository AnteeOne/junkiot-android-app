package tech.antee.junkiot.tensorflow_bridge.impl.audio.services.claps

import android.content.Context
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import tech.antee.junkiot.tensorflow_bridge.audio.core.models.AudioLabel
import tech.antee.junkiot.tensorflow_bridge.audio.services.claps.ClapsDetectionState
import tech.antee.junkiot.tensorflow_bridge.audio.services.claps.ClapsDetector
import tech.antee.junkiot.tensorflow_bridge.audio.services.claps.ClapsDetectorSettings
import tech.antee.junkiot.tensorflow_bridge.audio.services.sound_detection.DetectorState
import tech.antee.junkiot.tensorflow_bridge.impl.audio.core.delegate.AudioClassifierDelegate
import javax.inject.Inject

@FlowPreview
class ClapsDetectorImpl @Inject constructor(
    context: Context,
    settings: ClapsDetectorSettings
) : ClapsDetector, AudioClassifierDelegate(context, Meta.audioLabels) {

    private val _detectorState: MutableStateFlow<DetectorState> =
        MutableStateFlow(DetectorState.Empty)
    override val detectorState: Flow<DetectorState> = _detectorState

    private val _clapsDetectionState: MutableStateFlow<ClapsDetectionState> =
        MutableStateFlow(ClapsDetectionState.Empty)
    override val clapsDetectionState: Flow<ClapsDetectionState> = _clapsDetectionState
        .debounce(settings.debounceMs)

    override val onInitialized = {
        _detectorState.value = DetectorState.Disabled
    }

    override fun classifyAudio() {
        tensorAudio?.load(recorder)
        val output = audioClassifier?.classify(tensorAudio)
        val label = output
            ?.getOrNull(0)
            ?.categories
            ?.getOrNull(0)
            ?.label
        when (label) {
            AudioLabel.Clapping.labelValue,
            AudioLabel.Hands.labelValue,
            AudioLabel.FingerSnapping.labelValue -> _clapsDetectionState.value = ClapsDetectionState.Clap
            else -> _clapsDetectionState.value = ClapsDetectionState.Empty
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
    val audioLabels = listOf(
        AudioLabel.Nothing,
        AudioLabel.Clapping,
        AudioLabel.Hands,
        AudioLabel.FingerSnapping
    )
}
