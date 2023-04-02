package tech.antee.junkiot.main.impl.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [MainModule::class]
)
interface MainComponent
