package tech.antee.junkiot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import tech.antee.junkiot.controller.list.impl.di.LocalControllerListDependencies
import tech.antee.junkiot.di.LocalAppProvider
import tech.antee.junkiot.main.MainFeature
import tech.antee.junkiot.multi_compose.find
import tech.antee.junkiot.simulator.claps_detector.impl.di.LocalClapsDetectorSimulatorDependencies
import tech.antee.junkiot.simulator.light_sensor.impl.di.LocalLightSensorSimulatorDependencies
import tech.antee.junkiot.simulator.list.impl.di.LocalSimulatorListDependencies
import tech.antee.junkiot.styles.theme.JunkiotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JunkiotTheme(useDynamicColors = true) {
                SystemUiControllerHandler {
                    GlobalDependenciesProvider {
                        Navigation()
                    }
                }
            }
        }
    }

    @Composable
    private fun Navigation(modifier: Modifier = Modifier) {
        val navController = rememberNavController()
        val destinations = LocalAppProvider.current.destinations
        val mainFeature = destinations.find<MainFeature>()

        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
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
            LocalClapsDetectorSimulatorDependencies provides application.appProvider,
            content = content
        )
    }

    @Composable
    private fun SystemUiControllerHandler(
        content: @Composable () -> Unit
    ) {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = !isSystemInDarkTheme()
        val barColor = MaterialTheme.colorScheme.background

        DisposableEffect(systemUiController, useDarkIcons) {
            systemUiController.setSystemBarsColor(
                color = barColor,
                darkIcons = useDarkIcons
            )
            onDispose {}
        }
        content()
    }
}
