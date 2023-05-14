package tech.antee.junkiot.simulator.noise_sensor.impl.ui.views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import tech.antee.junkiot.simulator.noise_sensor.impl.ui.NoiseSensorSimulatorViewModel
import tech.antee.junkiot.simulator.noise_sensor.impl.ui.items.Action
import tech.antee.junkiot.simulator.noise_sensor.impl.ui.items.Event
import tech.antee.junkiot.styles.theme.Dimensions
import tech.antee.junkiot.ui.views.app_bar.CenteredAppBarWithBackButton
import tech.antee.junkiot.ui.views.spacing.VerticalSpacer

@Composable
fun NoiseSensorSimulatorScreen(
    viewModel: NoiseSensorSimulatorViewModel,
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
                }
//                Button(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = Dimensions.paddingHorizontalM)
//                        .padding(bottom = Dimensions.paddingVerticalXl),
//                    enabled = !isLoading,
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = when (sensorManagerState) {
//                            LightSensorManagerState.Enabled -> MaterialTheme.colorScheme.secondary
//                            else -> MaterialTheme.colorScheme.primary
//                        },
//                        disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
//                        disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4f)
//                    ),
//                    onClick = { viewModel.onAction(Action.OnStartBtnClick) }
//                ) {
//                    Text(
//                        color = MaterialTheme.colorScheme.onPrimary,
//                        style = MaterialTheme.typography.bodySmall,
//                        fontWeight = FontWeight.SemiBold,
//                        text = when (sensorManagerState) {
//                            LightSensorManagerState.Enabled -> "Stop"
//                            else -> "Start"
//                        }
//                    )
//                }
            }

            if (isLoading) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
