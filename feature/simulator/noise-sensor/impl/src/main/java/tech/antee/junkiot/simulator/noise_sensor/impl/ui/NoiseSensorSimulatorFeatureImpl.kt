package tech.antee.junkiot.simulator.noise_sensor.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import tech.antee.junkiot.multi_compose.Destinations
import tech.antee.junkiot.multi_compose.di.injectedViewModel
import tech.antee.junkiot.multi_compose.ui.LocalNavigationBarState
import tech.antee.junkiot.simulator.noise_sensor.impl.di.DaggerNoiseSensorSimulatorComponent
import tech.antee.junkiot.simulator.noise_sensor.impl.di.LocalNoiseSensorSimulatorDependencies
import tech.antee.junkiot.simulator.noise_sensor.impl.ui.views.NoiseSensorSimulatorScreen
import tech.antee.junkiot.simulator.noise_sensor.ui.NoiseSensorSimulatorFeature
import javax.inject.Inject

class NoiseSensorSimulatorFeatureImpl @Inject constructor() : NoiseSensorSimulatorFeature() {

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
            val deps = LocalNoiseSensorSimulatorDependencies.current
            val viewModel = injectedViewModel {
                DaggerNoiseSensorSimulatorComponent
                    .factory()
                    .create(deps, controllerId)
                    .viewModel
            }
            NoiseSensorSimulatorScreen(
                viewModel = viewModel,
                onNavBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}
