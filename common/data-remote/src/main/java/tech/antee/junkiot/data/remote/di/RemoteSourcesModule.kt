package tech.antee.junkiot.data.remote.di

import dagger.Binds
import dagger.Module
import tech.antee.junkiot.controll.impl.common.network.sources.ControllerRemoteSource
import tech.antee.junkiot.controll.impl.common.network.sources.ControllerRemoteSourceImpl
import javax.inject.Singleton

@Module
interface RemoteSourcesModule {

    @Singleton
    @Binds
    fun controllerRemoteSource(impl: ControllerRemoteSourceImpl): ControllerRemoteSource
}
