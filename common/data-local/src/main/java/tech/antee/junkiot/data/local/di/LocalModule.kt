package tech.antee.junkiot.data.local.di

import dagger.Binds
import dagger.Module
import tech.antee.junkiot.controll.impl.common.local.sources.ControllerLocalSource
import tech.antee.junkiot.controll.impl.common.local.sources.ControllerLocalSourceImpl
import tech.antee.junkiot.controll.impl.common.local.sources.SimulatorLocalSource
import tech.antee.junkiot.controll.impl.common.local.sources.SimulatorLocalSourceImpl
import tech.antee.junkiot.controll.impl.light_sensor.local.sources.LightSensorLocalSource
import tech.antee.junkiot.controll.impl.light_sensor.local.sources.LightSensorLocalSourceImpl
import javax.inject.Singleton

@Module(includes = [RoomModule::class])
interface LocalModule {

    @Singleton
    @Binds
    fun controllerLocalSource(impl: ControllerLocalSourceImpl): ControllerLocalSource

    @Singleton
    @Binds
    fun simulatorLocalSource(impl: SimulatorLocalSourceImpl): SimulatorLocalSource

    @Singleton
    @Binds
    fun lightSensorLocalSource(impl: LightSensorLocalSourceImpl): LightSensorLocalSource
}
