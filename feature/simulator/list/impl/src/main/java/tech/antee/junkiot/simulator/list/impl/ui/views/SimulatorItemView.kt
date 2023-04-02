package tech.antee.junkiot.simulator.list.impl.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.tooling.preview.Preview
import tech.antee.junkiot.controll.common.models.ControllerType
import tech.antee.junkiot.simulator.list.impl.ui.items.SimulatorItem
import tech.antee.junkiot.styles.theme.Dimensions
import tech.antee.junkiot.styles.theme.JunkiotTheme
import tech.antee.junkiot.ui.ktx.iconId
import tech.antee.junkiot.ui.views.spacing.HorizontalSpacer
import tech.antee.junkiot.ui.views.spacing.VerticalSpacer
import tech.antee.junkiot.ui_components.R as UiComponentsR

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SimulatorItemView(
    modifier: Modifier = Modifier,
    simulatorItem: SimulatorItem
) {
    Card(
        onClick = {},
        modifier = modifier,
        shape = RoundedCornerShape(Dimensions.cornersM),
        backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
        elevation = Dimensions.elevation
    ) {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = Dimensions.paddingHorizontalM,
                    vertical = Dimensions.paddingVerticalS
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically
        ) {
            Row {
                Icon(
                    modifier = Modifier
                        .align(CenterVertically)
                        .size(Dimensions.sizeIconL),
                    painter = painterResource(simulatorItem.controllerType.iconId()),
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = simulatorItem.controllerType.name
                )
                HorizontalSpacer(Dimensions.spacingHorizontalS)
                Column {
                    Text(
                        text = simulatorItem.name,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Icon(
                modifier = Modifier
                    .size(Dimensions.sizeIconM),
                painter = painterResource(UiComponentsR.drawable.ic_arrow_right),
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = simulatorItem.controllerType.name
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    JunkiotTheme(true) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            SimulatorItemView(
                modifier = Modifier.fillMaxWidth(),
                simulatorItem = SimulatorItem(
                    controllerType = ControllerType.LIGHT_SENSOR,
                    id = 23,
                    name = "Bedroom sensor",
                )
            )
            VerticalSpacer(height = Dimensions.spacingHorizontalXs)
            SimulatorItemView(
                modifier = Modifier.fillMaxWidth(),
                simulatorItem = SimulatorItem(
                    controllerType = ControllerType.LIGHT_SENSOR,
                    id = 23,
                    name = "Kitchen sensor"
                )
            )
        }
    }
}
