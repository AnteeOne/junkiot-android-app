package tech.antee.junkiot.di

import androidx.compose.runtime.compositionLocalOf
import tech.antee.junkiot.controller.list.impl.di.ControllerListDependencies
import tech.antee.junkiot.multi_compose.Destinations
import tech.antee.junkiot.simulator.light_sensor.impl.di.LightSensorSimulatorDependencies
import tech.antee.junkiot.simulator.list.impl.di.SimulatorListDependencies

interface AppProvider :
    ControllerListDependencies,
    SimulatorListDependencies,
    LightSensorSimulatorDependencies
{
    val destinations: Destinations
}

val LocalAppProvider = compositionLocalOf<AppProvider> { error("No app provider found!") }
