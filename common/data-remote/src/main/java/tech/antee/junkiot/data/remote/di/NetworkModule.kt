package tech.antee.junkiot.data.remote.di

import dagger.Module

@Module(
    includes = [NetworkApiModule::class, RemoteSourcesModule::class]
)
class NetworkModule
