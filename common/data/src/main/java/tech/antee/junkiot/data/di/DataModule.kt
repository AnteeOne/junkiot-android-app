package tech.antee.junkiot.data.di

import dagger.Module
import tech.antee.junkiot.data.local.di.LocalModule
import tech.antee.junkiot.data.remote.di.NetworkModule

@Module(
    includes = [
        NetworkModule::class,
        LocalModule::class
    ]
)
interface DataModule
