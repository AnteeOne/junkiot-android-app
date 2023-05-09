package tech.antee.junkiot.tensorflow_bridge.impl.audio.core.ktx

import org.tensorflow.lite.task.audio.classifier.AudioClassifier
import tech.antee.junkiot.tensorflow_bridge.audio.core.models.AudioLabel

fun AudioClassifier.AudioClassifierOptions.Builder.setAudioLabelAllowList(labels: List<AudioLabel>): AudioClassifier.AudioClassifierOptions.Builder =
    setLabelAllowList(labels.map { it.labelValue })
