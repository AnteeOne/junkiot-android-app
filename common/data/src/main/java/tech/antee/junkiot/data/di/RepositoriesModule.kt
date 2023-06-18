package tech.antee.junkiot.data.di

import dagger.Binds
import dagger.Module
import tech.antee.junkiot.controll.claps_detector.repositories.ClapsDetectorRepository
import tech.antee.junkiot.controll.common.repositories.ControllerRepository
import tech.antee.junkiot.controll.impl.claps_detector.repositories.ClapsDetectorRepositoryImpl
import tech.antee.junkiot.controll.impl.common.repositories.ControllerRepositoryImpl
import tech.antee.junkiot.controll.impl.light_sensor.repositories.LightSensorRepositoryImpl
import tech.antee.junkiot.controll.impl.noise_detector.repositories.NoiseDetectorRepositoryImpl
import tech.antee.junkiot.controll.light_sensor.repositories.LightSensorRepository
import tech.antee.junkiot.controll.noise_detector.repositories.NoiseDetectorRepository
import javax.inject.Singleton

@Module
interface RepositoriesModule {

    @Singleton
    @Binds
    fun controllerRepository(impl: ControllerRepositoryImpl): ControllerRepository

    @Singleton
    @Binds
    fun lightSensorRepository(impl: LightSensorRepositoryImpl): LightSensorRepository

    @Singleton
    @Binds
    fun clapsDetectorRepository(impl: ClapsDetectorRepositoryImpl): ClapsDetectorRepository

    @Singleton
    @Binds
    fun noiseDetectorRepository(impl: NoiseDetectorRepositoryImpl): NoiseDetectorRepository
}
