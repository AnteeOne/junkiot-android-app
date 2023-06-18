package tech.antee.junkiot.simulator.noise_sensor.impl.di

import android.content.Context
import androidx.compose.runtime.compositionLocalOf
import tech.antee.junkiot.controll.common.usecases.ObserveSimulatorUsecase
import tech.antee.junkiot.controll.noise_detector.usecases.AddNoiseDetectionUsecase
import tech.antee.junkiot.controll.noise_detector.usecases.ObserveNoiseDetectionsUsecase
import tech.antee.junkiot.di.qualifiers.ApplicationContext

interface NoiseSensorSimulatorDependencies {

    @ApplicationContext
    fun context(): Context

    val observeSimulatorUsecase: ObserveSimulatorUsecase

    val observeNoiseDetectionsUsecase: ObserveNoiseDetectionsUsecase

    val addNoiseDetectionsUsecase: AddNoiseDetectionUsecase
}

val LocalNoiseSensorSimulatorDependencies = compositionLocalOf<NoiseSensorSimulatorDependencies> {
    error("No feature deps provider found!")
}
