package tech.antee.junkiot.simulator.claps_detector.impl.ui.views

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import tech.antee.junkiot.simulator.claps_detector.impl.ui.ClapsDetectorSimulatorViewModel
import tech.antee.junkiot.simulator.claps_detector.impl.ui.items.Action
import tech.antee.junkiot.simulator.claps_detector.impl.ui.items.DetectorUiState
import tech.antee.junkiot.simulator.claps_detector.impl.ui.items.Event
import tech.antee.junkiot.styles.theme.Dimensions
import tech.antee.junkiot.ui.views.app_bar.CenteredAppBarWithBackButton
import tech.antee.junkiot.ui.views.spacing.VerticalSpacer

@Composable
fun ClapsDetectorSimulatorScreen(
    viewModel: ClapsDetectorSimulatorViewModel,
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
                    PulsatingBubble(
                        isAnimating = detectorState is DetectorUiState.Enabled,
                        modifier = Modifier
                            .size(240.dp)
                            .align(CenterHorizontally),
                        colored = clapped,
                        content = {}
                    )
                    VerticalSpacer(Dimensions.spacingVerticalXxl)
                    VerticalSpacer(Dimensions.spacingVerticalXxl)
                    clapDetections?.let { data ->
                        ClapsDetectionsValuesCard(data = data)
                    }
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

@Composable
private fun PulsatingBubble(
    isAnimating: Boolean,
    colored: Boolean,
    modifier: Modifier = Modifier,
    pulseFraction: Float = 1.2f,
    content: @Composable () -> Unit
) {
    val color by animateColorAsState(
        targetValue = if (colored) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.primary
    )

    Pulsating(
        modifier = modifier,
        isAnimating = isAnimating,
        pulseFraction = pulseFraction
    ) {
        Surface(
            modifier = Modifier.size(240.dp),
            color = color,
            shape = CircleShape,
            content = content
        )
    }
}

@Composable
fun Pulsating(
    isAnimating: Boolean,
    modifier: Modifier = Modifier,
    pulseFraction: Float,
    content: @Composable () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition()
    val initialScale = 1f

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = pulseFraction,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(modifier = modifier.scale(if (isAnimating) scale else initialScale)) {
        content()
    }
}
