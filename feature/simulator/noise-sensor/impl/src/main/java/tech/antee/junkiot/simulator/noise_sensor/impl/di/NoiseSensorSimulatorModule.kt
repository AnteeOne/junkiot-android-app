package tech.antee.junkiot.simulator.noise_sensor.impl.di

import android.content.Context
import dagger.Module
import dagger.Provides
import tech.antee.junkiot.di.qualifiers.ApplicationContext
import tech.antee.junkiot.di.scopes.FeatureScope
import tech.antee.junkiot.tensorflow_bridge.audio.services.noise.NoiseDetector
import tech.antee.junkiot.tensorflow_bridge.audio.services.noise.NoiseDetectorSettings
import tech.antee.junkiot.tensorflow_bridge.impl.audio.services.noise.NoiseDetectorImpl

@Module(
    includes = [MappersModule::class]
)
class NoiseSensorSimulatorModule {

    @Provides
    @FeatureScope
    fun noiseDetectorSettings(): NoiseDetectorSettings = object : NoiseDetectorSettings {
        override val debounceMs: Long = 500L
    }

    @Provides
    @FeatureScope
    fun noiseDetector(
        @ApplicationContext context: Context,
        noiseDetectorSettings: NoiseDetectorSettings
    ): NoiseDetector = NoiseDetectorImpl(
        context = context,
        settings = noiseDetectorSettings
    ).apply {
        initialize()
    }
}
