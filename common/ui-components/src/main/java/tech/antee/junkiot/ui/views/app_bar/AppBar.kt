package tech.antee.junkiot.ui.views.app_bar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import tech.antee.junkiot.styles.theme.Dimensions
import tech.antee.junkiot.ui_components.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenteredAppBar(
    modifier: Modifier = Modifier,
    title: String?,
    navigationIcon: @Composable (() -> Unit) = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.SemiBold,
                text = title ?: ""
            )
        },
        navigationIcon = navigationIcon,
        actions = actions,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}

@Composable
fun CenteredAppBarWithBackButton(
    modifier: Modifier = Modifier,
    title: String?,
    onBack: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) = CenteredAppBar(
    modifier = modifier,
    title = title,
    actions = actions,
    navigationIcon = {
        IconButton(onClick = onBack) {
            Icon(
                modifier = Modifier.size(Dimensions.sizeIconM),
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
)
