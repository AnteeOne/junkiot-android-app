package tech.antee.junkiot.data.local.di

import dagger.Binds
import dagger.Module
import tech.antee.junkiot.controll.impl.claps_detector.local.sources.ClapsDetectorLocalSource
import tech.antee.junkiot.controll.impl.claps_detector.local.sources.ClapsDetectorLocalSourceImpl
import tech.antee.junkiot.controll.impl.common.local.sources.ControllerLocalSource
import tech.antee.junkiot.controll.impl.common.local.sources.ControllerLocalSourceImpl
import tech.antee.junkiot.controll.impl.common.local.sources.SimulatorLocalSource
import tech.antee.junkiot.controll.impl.common.local.sources.SimulatorLocalSourceImpl
import tech.antee.junkiot.controll.impl.light_sensor.local.sources.LightSensorLocalSource
import tech.antee.junkiot.controll.impl.light_sensor.local.sources.LightSensorLocalSourceImpl
import tech.antee.junkiot.controll.impl.noise_detector.local.sources.NoiseDetectorLocalSource
import tech.antee.junkiot.controll.impl.noise_detector.local.sources.NoiseDetectorLocalSourceImpl
import javax.inject.Singleton

@Module
interface LocalSourcesModule {

    @Singleton
    @Binds
    fun controllerLocalSource(impl: ControllerLocalSourceImpl): ControllerLocalSource

    @Singleton
    @Binds
    fun simulatorLocalSource(impl: SimulatorLocalSourceImpl): SimulatorLocalSource

    @Singleton
    @Binds
    fun lightSensorLocalSource(impl: LightSensorLocalSourceImpl): LightSensorLocalSource

    @Singleton
    @Binds
    fun clapsDetectorLocalSource(impl: ClapsDetectorLocalSourceImpl): ClapsDetectorLocalSource

    @Singleton
    @Binds
    fun noiseDetectorLocalSource(impl: NoiseDetectorLocalSourceImpl): NoiseDetectorLocalSource
}
