package tech.antee.junkiot.styles.ktx

import android.content.Context
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme

fun dynamicColorScheme(
    context: Context,
    useDarkTheme: Boolean
) = if (useDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
