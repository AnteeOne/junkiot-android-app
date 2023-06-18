package tech.antee.junkiot.simulator.claps_detector.impl.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import tech.antee.junkiot.controll.claps_detector.models.ClapDetection
import tech.antee.junkiot.styles.theme.Dimensions
import tech.antee.junkiot.styles.theme.JunkiotTheme
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ClapsDetectionsValuesCard(
    modifier: Modifier = Modifier,
    data: List<ClapDetection>
) = Card(
    modifier = modifier,
    shape = RoundedCornerShape(Dimensions.cornersM),
    colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    )
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimensions.paddingVerticalM),
        content = {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(
                            horizontal = Dimensions.paddingHorizontalM,
                            vertical = Dimensions.paddingVerticalXxs
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "ID",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Date",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimensions.paddingHorizontalM),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            items(
                items = data,
                key = { clapDetection -> clapDetection.id }
            ) { clapDetection ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = Dimensions.paddingHorizontalM,
                            vertical = Dimensions.paddingVerticalXxs
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = clapDetection.id.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    val sdf = SimpleDateFormat("HH:mm:ss dd.MM.yyyy", Locale.US)
                    val date = Date(clapDetection.timeStamp)
                    Text(
                        text = sdf.format(date),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun ClapsDetectionsValuesCardPreview() = JunkiotTheme {
    Box(modifier = Modifier.fillMaxWidth()) {
        ClapsDetectionsValuesCard(
            data = listOf(
                ClapDetection(0, 0, 1687630518),
                ClapDetection(1, 0, 1687630528),
                ClapDetection(2, 0, 1687630534),
                ClapDetection(3, 0, 1687630599)
            )
        )
    }
}
