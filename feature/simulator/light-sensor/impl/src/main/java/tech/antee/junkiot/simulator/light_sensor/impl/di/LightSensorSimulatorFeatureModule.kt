package tech.antee.junkiot.simulator.light_sensor.impl.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tech.antee.junkiot.multi_compose.Feature
import tech.antee.junkiot.multi_compose.di.FeatureKey
import tech.antee.junkiot.simulator.light_sensor.ui.LightSensorSimulatorFeature
import tech.antee.junkiot.simulator.light_sensor.impl.ui.LightSensorSimulatorFeatureImpl
import javax.inject.Singleton

@Module
interface LightSensorSimulatorFeatureModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureKey(LightSensorSimulatorFeature::class)
    fun lightSensorSimulatorFeature(feature: LightSensorSimulatorFeatureImpl): Feature
}
