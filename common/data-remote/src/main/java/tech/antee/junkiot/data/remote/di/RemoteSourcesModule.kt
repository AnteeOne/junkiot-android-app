package tech.antee.junkiot.data.remote.di

import dagger.Binds
import dagger.Module
import tech.antee.junkiot.controll.impl.claps_detector.remote.sources.ClapsDetectorRemoteSource
import tech.antee.junkiot.controll.impl.claps_detector.remote.sources.ClapsDetectorRemoteSourceImpl
import tech.antee.junkiot.controll.impl.common.remote.sources.ControllerRemoteSource
import tech.antee.junkiot.controll.impl.common.remote.sources.ControllerRemoteSourceImpl
import tech.antee.junkiot.controll.impl.light_sensor.remote.sources.LightSensorRemoteSource
import tech.antee.junkiot.controll.impl.light_sensor.remote.sources.LightSensorRemoteSourceImpl
import tech.antee.junkiot.controll.impl.noise_detector.remote.sources.NoiseDetectorRemoteSource
import tech.antee.junkiot.controll.impl.noise_detector.remote.sources.NoiseDetectorRemoteSourceImpl
import javax.inject.Singleton

@Module
interface RemoteSourcesModule {

    @Singleton
    @Binds
    fun controllerRemoteSource(impl: ControllerRemoteSourceImpl): ControllerRemoteSource

    @Singleton
    @Binds
    fun lightSensorRemoteSource(impl: LightSensorRemoteSourceImpl): LightSensorRemoteSource

    @Singleton
    @Binds
    fun clapsDetectorRemoteSource(impl: ClapsDetectorRemoteSourceImpl): ClapsDetectorRemoteSource

    @Singleton
    @Binds
    fun noiseDetectorRemoteSource(impl: NoiseDetectorRemoteSourceImpl): NoiseDetectorRemoteSource
}
