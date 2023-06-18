package tech.antee.junkiot.tensorflow_bridge.impl.audio.core.delegate

import android.content.Context
import android.media.AudioRecord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import org.tensorflow.lite.support.audio.TensorAudio
import org.tensorflow.lite.task.audio.classifier.AudioClassifier
import org.tensorflow.lite.task.core.BaseOptions
import tech.antee.junkiot.tensorflow_bridge.audio.core.models.AudioLabel
import tech.antee.junkiot.tensorflow_bridge.audio.core.settings.AudioClassifierSettings
import tech.antee.junkiot.tensorflow_bridge.impl.audio.core.ktx.setAudioLabelAllowList
import tech.antee.junkiot.tensorflow_bridge.impl.audio.core.settings.DefaultAudioClassifierSettings
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

abstract class AudioClassifierDelegate @Inject constructor(
    private val context: Context,
    private val audioLabels: List<AudioLabel>,
    private val audioClassifierSettings: AudioClassifierSettings = DefaultAudioClassifierSettings
) {

    protected var audioClassifier: AudioClassifier? = null
    protected var tensorAudio: TensorAudio? = null
    protected var recorder: AudioRecord? = null

    private var executor: ScheduledThreadPoolExecutor? = null
    private val classifyRunnable = Runnable { classifyAudio() }

    protected open val onInitialized: (() -> Unit)? = null

    open fun initialize() {
        with(audioClassifierSettings) {
            val baseOptionsBuilder = BaseOptions.builder()
                .setNumThreads(classifyingThreadNum)
                .useNnapi()

            val options = AudioClassifier.AudioClassifierOptions.builder()
                .apply {
                    setScoreThreshold(displayThreshold)
                    setMaxResults(numOfResults)
                    setBaseOptions(baseOptionsBuilder.build())
                    if (audioLabels.isNotEmpty()) setAudioLabelAllowList(audioLabels)
                }
                .build()

            executor = ScheduledThreadPoolExecutor(recordingThreadsNum)

            CoroutineScope(executor!!.asCoroutineDispatcher()).launch {
                audioClassifier = AudioClassifier.createFromFileAndOptions(
                    context,
                    yamnetModelName,
                    options
                )
                tensorAudio = audioClassifier?.createInputTensorAudio()
                recorder = audioClassifier?.createAudioRecord()
                onInitialized?.invoke()
            }
        }
    }

    fun startAudioClassification() {
        if (recorder?.recordingState == AudioRecord.RECORDSTATE_RECORDING) {
            return
        }
        recorder?.startRecording()

        executor?.scheduleAtFixedRate(
            classifyRunnable,
            audioClassifierSettings.recordingDelayMs,
            audioClassifierSettings.recordingPeriodMs,
            TimeUnit.MILLISECONDS
        )
    }

    fun stopAudioClassification() {
        recorder?.stop()
        executor?.remove(classifyRunnable)
    }

    abstract fun classifyAudio()
}
