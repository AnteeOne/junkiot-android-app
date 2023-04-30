package tech.antee.junkiot.data.remote.di

import dagger.Binds
import dagger.Module
import tech.antee.junkiot.controll.impl.common.remote.sources.ControllerRemoteSource
import tech.antee.junkiot.controll.impl.common.remote.sources.ControllerRemoteSourceImpl
import tech.antee.junkiot.controll.impl.light_sensor.remote.sources.LightSensorRemoteSource
import tech.antee.junkiot.controll.impl.light_sensor.remote.sources.LightSensorRemoteSourceImpl
import javax.inject.Singleton

@Module
interface RemoteSourcesModule {

    @Singleton
    @Binds
    fun controllerRemoteSource(impl: ControllerRemoteSourceImpl): ControllerRemoteSource

    @Singleton
    @Binds
    fun lightSensorRemoteSource(impl: LightSensorRemoteSourceImpl): LightSensorRemoteSource
}
