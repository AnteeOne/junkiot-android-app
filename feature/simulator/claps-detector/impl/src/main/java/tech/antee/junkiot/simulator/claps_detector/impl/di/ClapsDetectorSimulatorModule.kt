package tech.antee.junkiot.simulator.claps_detector.impl.di

import android.content.Context
import dagger.Module
import dagger.Provides
import tech.antee.junkiot.di.qualifiers.ApplicationContext
import tech.antee.junkiot.di.scopes.FeatureScope
import tech.antee.junkiot.tensorflow_bridge.audio.services.claps.ClapsDetector
import tech.antee.junkiot.tensorflow_bridge.audio.services.claps.ClapsDetectorSettings
import tech.antee.junkiot.tensorflow_bridge.impl.audio.services.claps.ClapsDetectorImpl

@Module(
    includes = [MappersModule::class]
)
class ClapsDetectorSimulatorModule {

    @Provides
    @FeatureScope
    fun clapsDetectorSettings(): ClapsDetectorSettings = object : ClapsDetectorSettings {
        override val debounceMs: Long = 500L
    }

    @Provides
    @FeatureScope
    fun clapsDetector(
        @ApplicationContext context: Context,
        clapsDetectorSettings: ClapsDetectorSettings
    ): ClapsDetector = ClapsDetectorImpl(
        context = context,
        settings = clapsDetectorSettings
    ).apply {
        initialize()
    }
}
