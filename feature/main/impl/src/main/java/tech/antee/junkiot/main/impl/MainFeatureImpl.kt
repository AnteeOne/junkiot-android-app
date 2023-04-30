package tech.antee.junkiot.main.impl

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tech.antee.junkiot.controller.list.ControllerListFeature
import tech.antee.junkiot.main.MainFeature
import tech.antee.junkiot.main.impl.ui.NavigationBarScaffold
import tech.antee.junkiot.main.impl.ui.NavigationItem
import tech.antee.junkiot.multi_compose.Destinations
import tech.antee.junkiot.multi_compose.find
import tech.antee.junkiot.multi_compose.ui.LocalNavigationBarState
import tech.antee.junkiot.multi_compose.ui.rememberNavigationBarState
import tech.antee.junkiot.simulator.light_sensor.ui.LightSensorSimulatorFeature
import tech.antee.junkiot.simulator.list.SimulatorListFeature
import javax.inject.Inject

class MainFeatureImpl @Inject constructor() : MainFeature() {

    @Composable
    override fun NavGraphBuilder.Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        val controllersFeature = destinations.find<ControllerListFeature>()
        val simulatorsFeature = destinations.find<SimulatorListFeature>()
//        val settingsFeature = destinations.find<SettingsFeature>() TODO: add the remaining screens
        val lightSensorDetailsFeature = destinations.find<LightSensorSimulatorFeature>()

        val navigationItems = listOf(
            NavigationItem(R.drawable.ic_home, R.string.nav_home, controllersFeature.featureRoute),
            NavigationItem(R.drawable.ic_sensor, R.string.nav_simulator_mode, simulatorsFeature.featureRoute),
            NavigationItem(R.drawable.ic_settings, R.string.nav_settings, "settings")
        )
        // TODO(ISSUE): https://github.com/AnteeOne/junkiot-android-app/issues/4
        var selectedNavItem by remember { mutableStateOf(navigationItems[0]) }
        val mainNavController = rememberNavController().apply {
            addOnDestinationChangedListener { _, destination, _ ->
                navigationItems.find { it.route == destination.route }?.let {
                    selectedNavItem = it
                }
            }
        }
        CompositionLocalProvider(
            // TODO: handle in nav bar state by navigation args
            LocalNavigationBarState provides rememberNavigationBarState(initialValue = true)
        ) {
            NavigationBarScaffold(
                items = navigationItems,
                selectedItem = selectedNavItem,
                onClick = {
                    mainNavController.navigate(it.route) {
                        popUpTo(mainNavController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            ) {
                NavHost(
                    navController = mainNavController,
                    startDestination = controllersFeature.featureRoute
                ) {
                    with(controllersFeature) { composable(mainNavController, destinations) }
                    with(simulatorsFeature) { composable(mainNavController, destinations) }
                    composable("settings") { FeaturePlaceholder("Settings") }
                    with(lightSensorDetailsFeature) { composable(mainNavController, destinations) }
                }
            }
        }
    }

    @Composable
    fun FeaturePlaceholder(
        featureName: String,
        modifier: Modifier = Modifier
    ) = Box(modifier = modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = featureName,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
