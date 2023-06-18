package tech.antee.junkiot.simulator.noise_sensor.impl.ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.delay
import tech.antee.junkiot.styles.theme.Dimensions

@Composable
fun WavesBubble(
    isEnabled: Boolean,
    modifier: Modifier = Modifier,
    circleColor: Color = Color.Magenta,
    animationDelay: Int = 3000
) {
    val circles = listOf(
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) }
    )
    circles.forEachIndexed { index, animatable ->
        LaunchedEffect(isEnabled) {
            if (isEnabled) {
                delay(timeMillis = (animationDelay / 3L) * (index + 1))
                animatable.animateTo(
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            durationMillis = animationDelay,
                            easing = LinearEasing
                        ),
                        repeatMode = RepeatMode.Restart
                    )
                )
            } else {
                if (animatable.value != 0f) {
                    animatable.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(
                            durationMillis = animationDelay / 3,
                            easing = LinearEasing
                        )
                    )
                    animatable.snapTo(0f)
                }
                animatable.stop()
            }
        }
    }
    Box(
        modifier = modifier.background(color = Color.Transparent)
    ) {
        AnimatedVisibility(visible = !isEnabled) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(Dimensions.paddingHorizontalM),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                text = "Turn on the sensor for noise detection"
            )
        }
        circles.forEachIndexed { index, animatable ->
            Box(
                modifier = Modifier
                    .scale(scale = animatable.value)
                    .fillMaxSize()
                    .clip(shape = CircleShape)
                    .background(
                        color = circleColor
                            .copy(alpha = (1 - animatable.value))
                    )
            ) {
            }
        }
    }
}
