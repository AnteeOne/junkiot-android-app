package tech.antee.junkiot.simulator.list.impl.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.antee.junkiot.controll.common.models.ControllerType
import tech.antee.junkiot.simulator.list.impl.ui.items.SimulatorItem
import tech.antee.junkiot.styles.theme.Dimensions
import tech.antee.junkiot.styles.theme.JunkiotTheme
import tech.antee.junkiot.ui.ktx.iconId
import tech.antee.junkiot.ui.views.spacing.VerticalSpacer
import tech.antee.junkiot.ui_components.R as UiComponentsR

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SimulatorItemView(
    modifier: Modifier = Modifier,
    simulatorItem: SimulatorItem,
    onClick: (id: Int, controllerType: ControllerType) -> Unit
) {
    Card(
        onClick = {
            onClick(simulatorItem.id, simulatorItem.controllerType)
        },
        modifier = modifier,
        shape = RoundedCornerShape(Dimensions.cornersM),
        backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
        elevation = Dimensions.elevation
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = Dimensions.paddingHorizontalM,
                vertical = Dimensions.paddingVerticalS
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .align(CenterVertically)
                        .size(Dimensions.sizeIconL),
                    painter = painterResource(simulatorItem.controllerType.iconId()),
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = simulatorItem.controllerType.name
                )
                Icon(
                    modifier = Modifier
                        .size(Dimensions.sizeIconM),
                    painter = painterResource(UiComponentsR.drawable.ic_arrow_right),
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = simulatorItem.controllerType.name
                )
            }
            VerticalSpacer(height = Dimensions.spacingVerticalM)
            Text(
                text = simulatorItem.name,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    JunkiotTheme(true) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(Dimensions.paddingHorizontalXs),
            horizontalArrangement = Arrangement.spacedBy(Dimensions.spacingHorizontalXs),
            verticalArrangement = Arrangement.spacedBy(Dimensions.spacingHorizontalXs),
            columns = GridCells.Adaptive(minSize = 128.dp)
        ) {
            item {
                SimulatorItemView(
                    modifier = Modifier,
                    simulatorItem = SimulatorItem(
                        controllerType = ControllerType.LIGHT_SENSOR,
                        id = 23,
                        name = "Kitchen sensor"
                    ),
                    onClick = { _, _ -> }
                )
            }
            item {
                SimulatorItemView(
                    modifier = Modifier,
                    simulatorItem = SimulatorItem(
                        controllerType = ControllerType.LIGHT_SENSOR,
                        id = 23,
                        name = "Bedroom sensor"
                    ),
                    onClick = { _, _ -> }
                )
            }
        }
    }
}
