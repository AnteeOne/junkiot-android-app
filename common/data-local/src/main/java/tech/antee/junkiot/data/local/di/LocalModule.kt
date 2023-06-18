package tech.antee.junkiot.data.local.di

import dagger.Module

@Module(includes = [RoomModule::class, LocalSourcesModule::class])
interface LocalModule
