package tech.antee.junkiot.di

import androidx.compose.runtime.compositionLocalOf
import tech.antee.junkiot.controller.list.impl.di.ControllerListDependencies
import tech.antee.junkiot.multi_compose.Destinations
import tech.antee.junkiot.simulator.claps_detector.impl.di.ClapsDetectorSimulatorDependencies
import tech.antee.junkiot.simulator.light_sensor.impl.di.LightSensorSimulatorDependencies
import tech.antee.junkiot.simulator.list.impl.di.SimulatorListDependencies
import tech.antee.junkiot.simulator.noise_sensor.impl.di.NoiseSensorSimulatorDependencies

interface AppProvider :
    ControllerListDependencies,
    SimulatorListDependencies,
    LightSensorSimulatorDependencies,
    ClapsDetectorSimulatorDependencies,
    NoiseSensorSimulatorDependencies
{
    val destinations: Destinations
}

val LocalAppProvider = compositionLocalOf<AppProvider> { error("No app provider found!") }
