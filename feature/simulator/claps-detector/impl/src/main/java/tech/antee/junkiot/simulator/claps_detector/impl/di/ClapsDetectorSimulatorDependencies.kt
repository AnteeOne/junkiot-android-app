package tech.antee.junkiot.simulator.claps_detector.impl.di

import android.content.Context
import androidx.compose.runtime.compositionLocalOf
import tech.antee.junkiot.controll.common.usecases.ObserveSimulatorUsecase
import tech.antee.junkiot.di.qualifiers.ApplicationContext

interface ClapsDetectorSimulatorDependencies {

    @ApplicationContext
    fun context(): Context

    val observeSimulatorUsecase: ObserveSimulatorUsecase
}

val LocalClapsDetectorSimulatorDependencies = compositionLocalOf<ClapsDetectorSimulatorDependencies> {
    error("No feature deps provider found!")
}
