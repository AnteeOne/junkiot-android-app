package tech.antee.junkiot.multi_compose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Stable
class NavigationBarState(initialValue: Boolean) {
    var isVisible: Boolean by mutableStateOf(initialValue)
        private set

    fun show() {
        isVisible = true
    }

    fun hide() {
        isVisible = false
    }

    companion object {
        val Saver: Saver<NavigationBarState, *> = Saver(
            save = { it.isVisible },
            restore = { NavigationBarState(initialValue = it) }
        )
    }
}

@Composable
fun rememberNavigationBarState(
    initialValue: Boolean
): NavigationBarState {
    return rememberSaveable(saver = NavigationBarState.Saver) {
        NavigationBarState(initialValue = initialValue)
    }
}

val LocalNavigationBarState = compositionLocalOf<NavigationBarState> {
    NavigationBarState(false)
}
