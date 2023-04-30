package tech.antee.junkiot.simulator.light_sensor.impl.di

import android.content.Context
import androidx.compose.runtime.compositionLocalOf
import tech.antee.junkiot.controll.common.usecases.GetReactiveSimulatorUsecase
import tech.antee.junkiot.controll.light_sensor.usecases.AddLightSensorValueUsecase
import tech.antee.junkiot.controll.light_sensor.usecases.GetReactiveLightPredictionsUsecase
import tech.antee.junkiot.di.qualifiers.ApplicationContext

interface LightSensorSimulatorDependencies {

    @ApplicationContext
    fun context(): Context

    val getReactiveSimulatorUsecase: GetReactiveSimulatorUsecase

    val addLightSensorValueUsecase: AddLightSensorValueUsecase

    val getReactiveLightPredictionsUsecase: GetReactiveLightPredictionsUsecase
}

val LocalLightSensorSimulatorDependencies = compositionLocalOf<LightSensorSimulatorDependencies> {
    error("No feature deps provider found!")
}
