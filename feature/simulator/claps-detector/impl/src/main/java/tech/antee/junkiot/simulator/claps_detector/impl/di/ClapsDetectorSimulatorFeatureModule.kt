package tech.antee.junkiot.simulator.claps_detector.impl.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tech.antee.junkiot.multi_compose.Feature
import tech.antee.junkiot.multi_compose.di.FeatureKey
import tech.antee.junkiot.simulator.claps_detector.impl.ui.ClapsDetectorSimulatorFeatureImpl
import tech.antee.junkiot.simulator.claps_detector.ui.ClapsDetectorSimulatorFeature
import javax.inject.Singleton

@Module
interface ClapsDetectorSimulatorFeatureModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureKey(ClapsDetectorSimulatorFeature::class)
    fun clapsDetectorSimulatorFeature(feature: ClapsDetectorSimulatorFeatureImpl): Feature
}
