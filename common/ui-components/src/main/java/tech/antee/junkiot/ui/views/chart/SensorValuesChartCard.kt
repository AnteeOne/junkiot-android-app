package tech.antee.junkiot.ui.views.chart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.antee.junkiot.styles.theme.Dimensions
import tech.antee.junkiot.styles.theme.JunkiotTheme

@Composable
fun SensorValuesChartCard(
    modifier: Modifier = Modifier,
    data: List<Int>
) = Card(
    modifier = modifier,
    shape = RoundedCornerShape(Dimensions.cornersM),
    colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    )
) {
    LineChart(
        modifier = Modifier.fillMaxSize(),
        data = data.mapIndexed { index, value -> index.toLong() to value.toDouble() },
        graphColor = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Preview
@Composable
private fun SensorValuesChartCardPreview() = JunkiotTheme(useDarkTheme = true) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        SensorValuesChartCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            data = listOf(
                15, 10, 25, 120, 140, 130, 50, 10, 5, 3, 5, 2, 4, 120
            )
        )
    }
}
