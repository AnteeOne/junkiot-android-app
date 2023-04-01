package tech.antee.junkiot.di

import dagger.Module
import tech.antee.junkiot.controller.list.impl.di.ControllerListFeatureModule

@Module(
    includes = [ControllerListFeatureModule::class]
)
interface FeaturesModule
