package tech.antee.junkiot.di

import dagger.Module
import tech.antee.junkiot.controller.list.impl.di.ControllerListFeatureModule
import tech.antee.junkiot.main.impl.di.MainFeatureModule

@Module(
    includes = [
        MainFeatureModule::class,
        ControllerListFeatureModule::class
    ]
)
interface FeaturesModule
