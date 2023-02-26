package tech.antee.junkiot.di

import androidx.compose.runtime.compositionLocalOf
import tech.antee.junkiot.multi_compose.Destinations

interface AppProvider {
    val destinations: Destinations
}

val LocalAppProvider = compositionLocalOf<AppProvider> { error("No app provider found!") }
