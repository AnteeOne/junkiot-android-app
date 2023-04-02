package tech.antee.junkiot.di

import dagger.Module
import tech.antee.junkiot.controller.list.impl.di.ControllerListFeatureModule
import tech.antee.junkiot.main.impl.di.MainFeatureModule
import tech.antee.junkiot.simulator.list.impl.di.SimulatorListFeatureModule

@Module(
    includes = [
        MainFeatureModule::class,
        ControllerListFeatureModule::class,
        SimulatorListFeatureModule::class
    ]
)
interface FeaturesModule
