package tech.antee.junkiot.simulator.noise_sensor.impl.ui.views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import tech.antee.junkiot.simulator.noise_sensor.impl.ui.NoiseSensorSimulatorViewModel
import tech.antee.junkiot.simulator.noise_sensor.impl.ui.items.Action
import tech.antee.junkiot.simulator.noise_sensor.impl.ui.items.DetectorUiState
import tech.antee.junkiot.simulator.noise_sensor.impl.ui.items.Event
import tech.antee.junkiot.simulator.noise_sensor.impl.ui.items.NoiseDetectionUiState
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
                    WavesBubble(
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(horizontal = Dimensions.paddingVerticalM)
                            .fillMaxWidth()
                            .aspectRatio(1f),
                        isEnabled = detectorState is DetectorUiState.Enabled,
                        circleColor = when (noiseDetectionUiState) {
                            is NoiseDetectionUiState.Empty -> MaterialTheme.colorScheme.primary
                            is NoiseDetectionUiState.Noise -> MaterialTheme.colorScheme.secondary
                        }
                    )
                    VerticalSpacer(Dimensions.spacingVerticalXxs)
                    val noiseDetectionLabel = when (noiseDetectionUiState) {
                        is NoiseDetectionUiState.Empty -> "Nothing"
                        is NoiseDetectionUiState.Noise -> noiseDetectionUiState.label.labelValue
                    }
                    Text(
                        modifier = Modifier.align(CenterHorizontally),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        text = "Detected: $noiseDetectionLabel"
                    )
                    VerticalSpacer(Dimensions.spacingVerticalXs)
                    noiseDetections?.let { data -> NoiseDetectionsValuesCard(data = data) }
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimensions.paddingHorizontalM)
                        .padding(bottom = Dimensions.paddingVerticalXl),
                    enabled = !isLoading && detectorState !is DetectorUiState.Empty,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = when (detectorState) {
                            DetectorUiState.Empty,
                            DetectorUiState.Disabled -> MaterialTheme.colorScheme.primary
                            DetectorUiState.Enabled -> MaterialTheme.colorScheme.secondary
                        },
                        disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                        disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4f)
                    ),
                    onClick = { viewModel.onAction(Action.OnDetectBtnClick) }
                ) {
                    Text(
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        text = when (detectorState) {
                            DetectorUiState.Empty,
                            DetectorUiState.Disabled -> "Start"
                            DetectorUiState.Enabled -> "Stop"
                        }
                    )
                }
            }

            if (isLoading) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
