package tech.antee.junkiot.styles.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun LocalThemeProvider(
    content: @Composable() () -> Unit
) {
    CompositionLocalProvider(
        LocalIndication provides rememberRipple(bounded = true),
        content = content
    )
}
