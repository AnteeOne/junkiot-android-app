package tech.antee.junkiot.di

import dagger.Module
import dagger.Provides
import tech.antee.junkiot.multi_compose.Feature

@Module(
    includes = []
)
interface FeaturesModule {

    // TODO: delete this object after implementing at least one feature
    companion object {
        @Provides
        fun provideDefaultFeatures(): Map<Class<out Feature>, @JvmSuppressWildcards Feature> = emptyMap()
    }
}
