package tech.antee.junkiot.simulator.list.impl.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tech.antee.junkiot.multi_compose.Feature
import tech.antee.junkiot.multi_compose.di.FeatureKey
import tech.antee.junkiot.simulator.list.SimulatorListFeature
import tech.antee.junkiot.simulator.list.impl.ui.SimulatorListFeatureImpl
import javax.inject.Singleton

@Module
interface SimulatorListFeatureModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureKey(SimulatorListFeature::class)
    fun simulatorListFeature(feature: SimulatorListFeatureImpl): Feature
}
