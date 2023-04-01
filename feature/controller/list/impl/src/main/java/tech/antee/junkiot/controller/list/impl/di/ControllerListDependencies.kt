package tech.antee.junkiot.controller.list.impl.di

import androidx.compose.runtime.compositionLocalOf
import tech.antee.junkiot.controll.common.usecases.GetReactiveControllersUsecase
import tech.antee.junkiot.controll.common.usecases.ObserveRemoteControllersUsecase
import tech.antee.junkiot.di.Dependencies

interface ControllerListDependencies : Dependencies {

    val getReactiveControllersUsecase: GetReactiveControllersUsecase

    val observeRemoteControllersUsecase: ObserveRemoteControllersUsecase
}

val LocalControllerListDependencies = compositionLocalOf<ControllerListDependencies> {
    error("No feature deps provider found!")
}
