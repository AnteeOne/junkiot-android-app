package tech.antee.junkiot.simulator.list.impl.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import tech.antee.junkiot.multi_compose.Destinations
import tech.antee.junkiot.multi_compose.di.injectedViewModel
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
        val deps = LocalSimulatorListDependencies.current
        val viewModel = injectedViewModel {
            DaggerSimulatorListComponent.factory().create(deps).viewModel
        }
        SimulatorListScreen(viewModel = viewModel)
    }
}
