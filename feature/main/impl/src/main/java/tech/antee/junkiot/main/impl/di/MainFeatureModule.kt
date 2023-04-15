package tech.antee.junkiot.main.impl.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tech.antee.junkiot.main.MainFeature
import tech.antee.junkiot.main.impl.MainFeatureImpl
import tech.antee.junkiot.multi_compose.Feature
import tech.antee.junkiot.multi_compose.di.FeatureKey
import javax.inject.Singleton

@Module
interface MainFeatureModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureKey(MainFeature::class)
    fun mainContentFeature(feature: MainFeatureImpl): Feature
}
