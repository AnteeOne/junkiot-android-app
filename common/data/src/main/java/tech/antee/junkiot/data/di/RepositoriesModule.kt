package tech.antee.junkiot.data.di

import dagger.Binds
import dagger.Module
import tech.antee.junkiot.controll.common.repositories.ControllerRepository
import tech.antee.junkiot.controll.impl.common.repositories.ControllerRepositoryImpl
import javax.inject.Singleton

@Module
interface RepositoriesModule {

    @Singleton
    @Binds
    fun controllerRepository(impl: ControllerRepositoryImpl): ControllerRepository
}
