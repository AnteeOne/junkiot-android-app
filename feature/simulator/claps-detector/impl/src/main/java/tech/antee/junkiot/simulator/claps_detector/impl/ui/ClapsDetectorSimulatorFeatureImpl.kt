package tech.antee.junkiot.simulator.claps_detector.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import tech.antee.junkiot.multi_compose.Destinations
import tech.antee.junkiot.multi_compose.di.injectedViewModel
import tech.antee.junkiot.multi_compose.ui.LocalNavigationBarState
import tech.antee.junkiot.simulator.claps_detector.impl.di.DaggerClapsDetectorSimulatorComponent
import tech.antee.junkiot.simulator.claps_detector.impl.di.LocalClapsDetectorSimulatorDependencies
import tech.antee.junkiot.simulator.claps_detector.impl.ui.views.ClapsDetectorSimulatorScreen
import tech.antee.junkiot.simulator.claps_detector.ui.ClapsDetectorSimulatorFeature
import javax.inject.Inject

class ClapsDetectorSimulatorFeatureImpl @Inject constructor() : ClapsDetectorSimulatorFeature() {

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
            val deps = LocalClapsDetectorSimulatorDependencies.current
            val viewModel = injectedViewModel {
                DaggerClapsDetectorSimulatorComponent
                    .factory()
                    .create(deps, controllerId)
                    .viewModel
            }
            ClapsDetectorSimulatorScreen(
                viewModel = viewModel,
                onNavBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}
