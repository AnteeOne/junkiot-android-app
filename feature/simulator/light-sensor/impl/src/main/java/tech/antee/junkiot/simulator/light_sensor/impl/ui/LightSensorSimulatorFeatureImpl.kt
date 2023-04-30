package tech.antee.junkiot.simulator.light_sensor.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import tech.antee.junkiot.multi_compose.Destinations
import tech.antee.junkiot.multi_compose.di.injectedViewModel
import tech.antee.junkiot.multi_compose.ui.LocalNavigationBarState
import tech.antee.junkiot.simulator.light_sensor.ui.LightSensorSimulatorFeature
import tech.antee.junkiot.simulator.light_sensor.impl.di.DaggerLightSensorSimulatorComponent
import tech.antee.junkiot.simulator.light_sensor.impl.di.LocalLightSensorSimulatorDependencies
import tech.antee.junkiot.simulator.light_sensor.impl.ui.views.LightSensorSimulatorScreen
import javax.inject.Inject

class LightSensorSimulatorFeatureImpl @Inject constructor() : LightSensorSimulatorFeature() {

    @Composable
    override fun NavGraphBuilder.Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        val nabBarState = LocalNavigationBarState.current
        LaunchedEffect(nabBarState) {
            nabBarState.hide()
        }

        backStackEntry.arguments?.getInt(controllerIdArgument)?.let { controllerId ->
            val deps = LocalLightSensorSimulatorDependencies.current
            val viewModel = injectedViewModel {
                DaggerLightSensorSimulatorComponent
                    .factory()
                    .create(deps, controllerId)
                    .viewModel
            }
            LightSensorSimulatorScreen(viewModel)
        }
    }
}
