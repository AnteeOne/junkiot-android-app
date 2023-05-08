package tech.antee.junkiot.controller.list.impl.di

import androidx.compose.runtime.compositionLocalOf
import tech.antee.junkiot.controll.common.usecases.ObserveControllersUsecase
import tech.antee.junkiot.di.Dependencies

interface ControllerListDependencies : Dependencies {

    val observeControllersUsecase: ObserveControllersUsecase
}

val LocalControllerListDependencies = compositionLocalOf<ControllerListDependencies> {
    error("No feature deps provider found!")
}
