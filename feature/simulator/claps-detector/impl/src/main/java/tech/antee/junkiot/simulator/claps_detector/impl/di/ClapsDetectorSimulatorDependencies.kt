package tech.antee.junkiot.simulator.claps_detector.impl.di

import android.content.Context
import androidx.compose.runtime.compositionLocalOf
import tech.antee.junkiot.controll.claps_detector.usecases.ObserveClapDetectionsUsecase
import tech.antee.junkiot.controll.claps_detector.usecases.AddClapDetectionUsecase
import tech.antee.junkiot.controll.common.usecases.ObserveSimulatorUsecase
import tech.antee.junkiot.di.qualifiers.ApplicationContext

interface ClapsDetectorSimulatorDependencies {

    @ApplicationContext
    fun context(): Context

    val observeSimulatorUsecase: ObserveSimulatorUsecase

    val observeClapDetectionsUsecase: ObserveClapDetectionsUsecase

    val addClapDetectionsUsecase: AddClapDetectionUsecase
}

val LocalClapsDetectorSimulatorDependencies = compositionLocalOf<ClapsDetectorSimulatorDependencies> {
    error("No feature deps provider found!")
}
