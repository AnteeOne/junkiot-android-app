package tech.antee.junkiot.di

import dagger.Module
import tech.antee.junkiot.controller.list.impl.di.ControllerListFeatureModule
import tech.antee.junkiot.main.impl.di.MainFeatureModule
import tech.antee.junkiot.simulator.claps_detector.impl.di.ClapsDetectorSimulatorFeatureModule
import tech.antee.junkiot.simulator.light_sensor.impl.di.LightSensorSimulatorFeatureModule
import tech.antee.junkiot.simulator.list.impl.di.SimulatorListFeatureModule
import tech.antee.junkiot.simulator.noise_sensor.impl.di.NoiseSensorSimulatorFeatureModule

@Module(
    includes = [
        MainFeatureModule::class,
        ControllerListFeatureModule::class,
        SimulatorListFeatureModule::class,
        LightSensorSimulatorFeatureModule::class,
        ClapsDetectorSimulatorFeatureModule::class,
        NoiseSensorSimulatorFeatureModule::class
    ]
)
interface FeaturesModule
