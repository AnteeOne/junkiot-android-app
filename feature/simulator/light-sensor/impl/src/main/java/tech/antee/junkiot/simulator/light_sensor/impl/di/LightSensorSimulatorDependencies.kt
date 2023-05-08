package tech.antee.junkiot.simulator.light_sensor.impl.di

import android.content.Context
import androidx.compose.runtime.compositionLocalOf
import tech.antee.junkiot.controll.common.usecases.ObserveSimulatorUsecase
import tech.antee.junkiot.controll.light_sensor.usecases.AddLightSensorValueUsecase
import tech.antee.junkiot.controll.light_sensor.usecases.ObserveLightPredictionsUsecase
import tech.antee.junkiot.di.qualifiers.ApplicationContext

interface LightSensorSimulatorDependencies {

    @ApplicationContext
    fun context(): Context

    val observeSimulatorUsecase: ObserveSimulatorUsecase

    val addLightSensorValueUsecase: AddLightSensorValueUsecase

    val observeLightPredictionsUsecase: ObserveLightPredictionsUsecase
}

val LocalLightSensorSimulatorDependencies = compositionLocalOf<LightSensorSimulatorDependencies> {
    error("No feature deps provider found!")
}
