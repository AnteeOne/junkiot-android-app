package tech.antee.junkiot.simulator.list.impl.di

import androidx.compose.runtime.compositionLocalOf
import tech.antee.junkiot.controll.common.usecases.AddControllerUsecase
import tech.antee.junkiot.controll.common.usecases.ObserveSimulatorsUsecase
import tech.antee.junkiot.di.Dependencies

interface SimulatorListDependencies : Dependencies {

    val observeSimulatorsUsecase: ObserveSimulatorsUsecase

    val addControllerUsecase: AddControllerUsecase
}

val LocalSimulatorListDependencies = compositionLocalOf<SimulatorListDependencies> {
    error("No feature deps provider found!")
}
