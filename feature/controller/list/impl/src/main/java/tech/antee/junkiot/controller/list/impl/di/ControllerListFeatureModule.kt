package tech.antee.junkiot.controller.list.impl.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tech.antee.junkiot.controller.list.ControllerListFeature
import tech.antee.junkiot.controller.list.impl.ui.ControllerListFeatureImpl
import tech.antee.junkiot.multi_compose.Feature
import tech.antee.junkiot.multi_compose.di.FeatureKey
import javax.inject.Singleton

@Module
interface ControllerListFeatureModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureKey(ControllerListFeature::class)
    fun controllerListFeature(feature: ControllerListFeatureImpl): Feature
}
