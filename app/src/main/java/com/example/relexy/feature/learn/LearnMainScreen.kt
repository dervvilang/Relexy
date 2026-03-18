package com.example.relexy.feature.learn

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.relexy.R
import com.example.relexy.core.ui.components.BrandTitle
import com.example.relexy.core.ui.components.LowContrastButton
import com.example.relexy.core.ui.components.PrimaryCard
import com.example.relexy.core.ui.components.SecondaryCard
import com.example.relexy.core.ui.components.TertiaryCard
import com.example.relexy.core.ui.components.TodayCard
import com.example.relexy.core.ui.theme.RelexyTheme

@Composable
fun LearnMainScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            //.padding(all = 16.dp)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            BrandTitle()

            Spacer(Modifier.height(14.dp))

            Text(
                text = stringResource(R.string.learn_interval_repetition),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(8.dp))

            PrimaryCard(
                title = "Выбрано 5 словарей",
                leadingIcon = R.drawable.ic_pencil_1,
                icons = listOf(
                    R.drawable.ic_hospital,
                    R.drawable.ic_bluebook,
                    R.drawable.ic_americasglobe,
                    R.drawable.ic_americasglobe,
                ),
                onClick = {}
            )

            Spacer(Modifier.height(13.dp))

            TodayCard(learnedWordsCount = 5, totalWordsCount = 10)

            Spacer(Modifier.height(13.dp))

            SecondaryCard(
                onClick = {},
                title = stringResource(R.string.learn_review_words_title),
                subtitle = stringResource(R.string.learn_review_due, 12),
                leadIcon = R.drawable.ic_alarmclock_1,
                trailingContent = {
                    LowContrastButton(
                        text = stringResource(R.string.action_review),
                        onClick = {}
                    )
                }
            )

            Spacer(Modifier.height(28.dp))

            Text(
                text = stringResource(R.string.learn_additional_modes),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(8.dp))

            TertiaryCard(
                onClick = {},
                title = stringResource(R.string.learn_mixed_mode),
                leadIcon = R.drawable.ic_twistedrightwardsarrows_1,
                secondaryIcon = R.drawable.ic_chevron_right_2,
            )

            Spacer(Modifier.height(13.dp))

            TertiaryCard(
                onClick = {},
                title = stringResource(R.string.learn_auto_mode),
                leadIcon = R.drawable.ic_blackrightpointingdoublearrow_1,
                secondaryIcon = R.drawable.ic_chevron_right_2,
            )

            Spacer(Modifier.height(13.dp))

            TertiaryCard(
                onClick = {},
                title = stringResource(R.string.learn_browse_mode),
                leadIcon = R.drawable.ic_anticlock_arrows,
                secondaryIcon = R.drawable.ic_chevron_right_2,
            )
        }
    }
}

@Preview
@Composable
fun LearnMainScreenPreview() {
    RelexyTheme() {
        LearnMainScreen()
    }
}