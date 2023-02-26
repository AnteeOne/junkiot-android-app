package tech.antee.junkiot.di

import dagger.Module
import tech.antee.junkiot.data.di.DataModule

@Module(
    includes = [DataModule::class]
)
interface AppModule
