package tech.antee.junkiot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import tech.antee.junkiot.controller.list.impl.di.LocalControllerListDependencies
import tech.antee.junkiot.di.LocalAppProvider
import tech.antee.junkiot.main.MainFeature
import tech.antee.junkiot.multi_compose.find
import tech.antee.junkiot.simulator.light_sensor.impl.di.LocalLightSensorSimulatorDependencies
import tech.antee.junkiot.simulator.list.impl.di.LocalSimulatorListDependencies
import tech.antee.junkiot.styles.theme.JunkiotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JunkiotTheme(useDynamicColors = true) {
                GlobalDependenciesProvider {
                    Navigation()
                }
            }
        }
    }

    @Composable
    private fun Navigation(modifier: Modifier = Modifier) {
        val navController = rememberNavController()
        val destinations = LocalAppProvider.current.destinations
        val mainFeature = destinations.find<MainFeature>()

        Box(modifier.fillMaxSize()) {
            NavHost(navController, mainFeature.featureRoute) {
                with(mainFeature) { composable(navController, destinations) }
            }
        }
    }

    @Composable
    private fun GlobalDependenciesProvider(
        content: @Composable () -> Unit
    ) {
        CompositionLocalProvider(
            LocalAppProvider provides application.appProvider,
            LocalControllerListDependencies provides application.appProvider,
            LocalSimulatorListDependencies provides application.appProvider,
            LocalLightSensorSimulatorDependencies provides application.appProvider,
            content = content
        )
    }
}
