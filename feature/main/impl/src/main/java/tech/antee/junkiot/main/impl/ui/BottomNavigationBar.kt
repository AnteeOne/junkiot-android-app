package tech.antee.junkiot.main.impl.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import tech.antee.junkiot.main.impl.R
import tech.antee.junkiot.multi_compose.ui.LocalNavigationBarState
import tech.antee.junkiot.styles.theme.Dimensions
import tech.antee.junkiot.styles.theme.JunkiotTheme

@Composable
fun NavigationBarScaffold(
    items: List<NavigationItem>,
    selectedItem: NavigationItem,
    onClick: (NavigationItem) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val navigationBarState = LocalNavigationBarState.current

    // TODO(ISSUE): handle navigation bar height
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            content = content
        )
        AnimatedVisibility(
            modifier = modifier.align(Alignment.BottomCenter),
            visible = navigationBarState.isVisible,
            enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
        ) {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItem.route == item.route,
                        onClick = { onClick(item) },
                        icon = {
                            Icon(
                                modifier = Modifier.size(Dimensions.sizeIconM),
                                painter = painterResource(item.iconId),
                                contentDescription = stringResource(item.titleId),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(item.titleId),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun NavigationBarPreview() {
    JunkiotTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            val navigationItems = listOf(
                NavigationItem(R.drawable.ic_home, R.string.nav_home, "home"),
                NavigationItem(R.drawable.ic_sensor, R.string.nav_simulator_mode, "sensor_mode"),
                NavigationItem(R.drawable.ic_settings, R.string.nav_settings, "settings")
            )
            NavigationBarScaffold(
                items = navigationItems,
                selectedItem = navigationItems[0],
                onClick = {}
            ) {}
        }
    }
}
