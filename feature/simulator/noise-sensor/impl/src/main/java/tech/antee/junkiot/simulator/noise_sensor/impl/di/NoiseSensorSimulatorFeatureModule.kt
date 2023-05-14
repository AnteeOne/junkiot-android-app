package tech.antee.junkiot.simulator.noise_sensor.impl.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tech.antee.junkiot.multi_compose.Feature
import tech.antee.junkiot.multi_compose.di.FeatureKey
import tech.antee.junkiot.simulator.noise_sensor.impl.ui.NoiseSensorSimulatorFeatureImpl
import tech.antee.junkiot.simulator.noise_sensor.ui.NoiseSensorSimulatorFeature
import javax.inject.Singleton

@Module
interface NoiseSensorSimulatorFeatureModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureKey(NoiseSensorSimulatorFeature::class)
    fun noiseSensorSimulatorFeature(feature: NoiseSensorSimulatorFeatureImpl): Feature
}
