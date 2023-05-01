package tech.antee.junkiot.controller.list.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import tech.antee.junkiot.controller.list.ControllerListFeature
import tech.antee.junkiot.controller.list.impl.di.DaggerControllerListComponent
import tech.antee.junkiot.controller.list.impl.di.LocalControllerListDependencies
import tech.antee.junkiot.controller.list.impl.ui.views.ControllerListScreen
import tech.antee.junkiot.multi_compose.Destinations
import tech.antee.junkiot.multi_compose.di.injectedViewModel
import tech.antee.junkiot.multi_compose.ui.LocalNavigationBarState
import javax.inject.Inject

class ControllerListFeatureImpl @Inject constructor() : ControllerListFeature() {

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

        val deps = LocalControllerListDependencies.current
        val viewModel = injectedViewModel {
            DaggerControllerListComponent.factory().create(deps).viewModel
        }
        ControllerListScreen(viewModel = viewModel)
    }
}
