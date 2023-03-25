package tech.antee.junkiot.data.local.di

import dagger.Binds
import dagger.Module
import tech.antee.junkiot.controll.impl.common.local.sources.ControllerLocalSource
import tech.antee.junkiot.controll.impl.common.local.sources.ControllerLocalSourceImpl
import javax.inject.Singleton

@Module(includes = [RoomModule::class])
interface LocalModule {

    @Singleton
    @Binds
    fun controllerLocalSource(impl: ControllerLocalSourceImpl): ControllerLocalSource
}
