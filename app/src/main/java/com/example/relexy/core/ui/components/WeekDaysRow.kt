package com.example.relexy.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.relexy.R
import com.example.relexy.core.ui.theme.RelexyTheme
import com.example.relexy.domain.roughModels.WeekDayItem

@Composable
fun WeekDaysRow(
    modifier: Modifier = Modifier
) {
    val days = listOf(
        WeekDayItem(R.string.weekday_mon, false),
        WeekDayItem(R.string.weekday_tue, false),
        WeekDayItem(R.string.weekday_wed, true),
        WeekDayItem(R.string.weekday_thu, false),
        WeekDayItem(R.string.weekday_fri, false),
        WeekDayItem(R.string.weekday_sat, false),
        WeekDayItem(R.string.weekday_sun, false),
    )

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        days.forEach { day ->
            WeekDayChip(
                label = day.label,
                isCurrent = day.isCurrent,
                onClick = {},
            )
        }
    }
}

@Preview
@Composable
fun WeekDaysRowPreview() {
    RelexyTheme {
        WeekDaysRow(
        )
    }
}