package tech.antee.junkiot.simulator.list.impl.ui.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import tech.antee.junkiot.controll.common.models.ControllerType
import tech.antee.junkiot.simulator.list.impl.ui.SimulatorListViewModel
import tech.antee.junkiot.simulator.list.impl.ui.items.Event
import tech.antee.junkiot.styles.theme.Dimensions
import tech.antee.junkiot.ui.views.spacing.VerticalSpacer

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SimulatorListScreen(
    viewModel: SimulatorListViewModel,
    modifier: Modifier = Modifier,
    onNavToDetails: (id: Int, controllerType: ControllerType) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.uiEvents.collect { event ->
            when(event) {
                is Event.OnNavToDetails -> onNavToDetails(event.id, event.controllerType)
            }
        }
    }

    with(uiState) {
        Box(modifier = modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = Dimensions.paddingVerticalM)
            ) {
                VerticalSpacer(Dimensions.spacingVerticalXl)
                Text(
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.SemiBold,
                    text = "Simulators" // TODO: to strings
                )
                VerticalSpacer(Dimensions.spacingVerticalXl)
                LazyColumn( // TODO: move to a sерarate file
                    modifier = Modifier.weight(1f)
                ) {
                    items(
                        items = simulators,
                        key = { simulator -> simulator.id }
                    ) { item ->
                        SimulatorItemView(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateItemPlacement(),
                            simulatorItem = item,
                            onClick = onNavToDetails
                        )
                        Spacer(modifier = Modifier.height(Dimensions.spacingVerticalXs))
                    }
                }
            }
            if (isLoading) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
