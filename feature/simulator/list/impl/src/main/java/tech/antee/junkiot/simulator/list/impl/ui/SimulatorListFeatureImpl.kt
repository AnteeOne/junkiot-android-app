package tech.antee.junkiot.simulator.list.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import tech.antee.junkiot.controll.common.models.ControllerType
import tech.antee.junkiot.multi_compose.Destinations
import tech.antee.junkiot.multi_compose.di.injectedViewModel
import tech.antee.junkiot.multi_compose.find
import tech.antee.junkiot.multi_compose.ui.LocalNavigationBarState
import tech.antee.junkiot.simulator.light_sensor.ui.LightSensorSimulatorFeature
import tech.antee.junkiot.simulator.list.SimulatorListFeature
import tech.antee.junkiot.simulator.list.impl.di.DaggerSimulatorListComponent
import tech.antee.junkiot.simulator.list.impl.di.LocalSimulatorListDependencies
import tech.antee.junkiot.simulator.list.impl.ui.views.SimulatorListScreen
import javax.inject.Inject

class SimulatorListFeatureImpl @Inject constructor() : SimulatorListFeature() {

    @Composable
    override fun NavGraphBuilder.Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        val nabBarState = LocalNavigationBarState.current
        LaunchedEffect(nabBarState) {
            nabBarState.show()
        }

        val deps = LocalSimulatorListDependencies.current
        val viewModel = injectedViewModel {
            DaggerSimulatorListComponent
                .factory()
                .create(deps)
                .viewModel
        }
        val lightSensorSimulatorFeature = destinations.find<LightSensorSimulatorFeature>()
        SimulatorListScreen(
            viewModel = viewModel,
            onNavToDetails = { id, controllerType ->
                when (controllerType) {
                    ControllerType.LIGHT_SENSOR -> {
                        navController.navigate(lightSensorSimulatorFeature.destination(id))
                    }
                }
            }
        )
    }
}
