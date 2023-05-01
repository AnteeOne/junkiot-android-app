package tech.antee.junkiot.simulator.light_sensor.impl.ui.views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import tech.antee.junkiot.simulator.light_sensor.impl.ui.LightSensorSimulatorViewModel
import tech.antee.junkiot.simulator.light_sensor.impl.ui.items.Action
import tech.antee.junkiot.simulator.light_sensor.impl.ui.items.Event
import tech.antee.junkiot.simulator.light_sensor.impl.ui.items.LightPredictionUiState
import tech.antee.junkiot.simulator.light_sensor.impl.ui.items.LightSensorUiState
import tech.antee.junkiot.simulator.light_sensor.models.LightSensorManagerState
import tech.antee.junkiot.styles.theme.Dimensions
import tech.antee.junkiot.ui.views.app_bar.CenteredAppBarWithBackButton
import tech.antee.junkiot.ui.views.spacing.VerticalSpacer

@Composable
fun LightSensorSimulatorScreen(
    viewModel: LightSensorSimulatorViewModel,
    onNavBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current
    LaunchedEffect(viewModel) {
        viewModel.uiEvents.collect { event ->
            when (event) {
                is Event.ShowErrorSnackBar -> Toast.makeText(context, "Unknown error!", Toast.LENGTH_LONG).show()
                is Event.NavBack -> onNavBack()
            }
        }
    }

    with(uiState) {
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                CenteredAppBarWithBackButton(
                    title = simulator?.name,
                    onBack = { viewModel.onAction(Action.OnBackBtnClick) }
                )
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = Dimensions.paddingVerticalM)
                ) {
                    VerticalSpacer(Dimensions.spacingVerticalXxl)
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.displayLarge,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        text = when (lightSensorState) {
                            is LightSensorUiState.Value -> String.format("%.2f", lightSensorState.lux)
                            else -> "0"
                        }
                    )
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        text = "lux"
                    )
                    VerticalSpacer(Dimensions.spacingVerticalXxl)
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        text = when (lightPredictionsState) {
                            is LightPredictionUiState.Empty -> "Unknown"
                            is LightPredictionUiState.Value -> lightPredictionsState.lightPrediction.name
                        }
                    )
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimensions.paddingHorizontalM)
                        .padding(bottom = Dimensions.paddingVerticalXl),
                    enabled = !isLoading,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = when (sensorManagerState) {
                            LightSensorManagerState.Enabled -> MaterialTheme.colorScheme.secondary
                            else -> MaterialTheme.colorScheme.primary
                        },
                        disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                        disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4f)
                    ),
                    onClick = { viewModel.onAction(Action.OnStartBtnClick) }
                ) {
                    Text(
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        text = when (sensorManagerState) {
                            LightSensorManagerState.Enabled -> "Stop"
                            else -> "Start"
                        }
                    )
                }
            }

            if (isLoading) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
