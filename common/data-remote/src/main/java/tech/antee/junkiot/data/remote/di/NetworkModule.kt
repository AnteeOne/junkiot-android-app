package tech.antee.junkiot.data.remote.di

import dagger.Module

@Module(
    includes = [RetrofitModule::class, NetworkSourcesModule::class]
)
interface NetworkModule
