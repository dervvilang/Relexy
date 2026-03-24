package com.example.relexy.feature.dictionary

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.relexy.R
import com.example.relexy.core.ui.components.SearchCard
import com.example.relexy.core.ui.components.SecondaryCard
import com.example.relexy.core.ui.components.TertiaryCard
import com.example.relexy.core.ui.theme.RelexyTheme

@Composable
fun DictionaryMainScreen() {
    val focusManager = LocalFocusManager.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .padding(horizontal = 16.dp)
            .pointerInput(Unit) { detectTapGestures { focusManager.clearFocus() } },
        contentAlignment = Alignment.TopCenter
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.nav_dictionaries),
                    modifier = Modifier
                        .padding(start = 8.dp),
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                TextButton(
                    onClick = {},
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = stringResource(R.string.dictionaries_import),
                        modifier = Modifier
                            .padding(end = 8.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(Modifier.height(13.dp))

            SearchCard(stringResource(R.string.dictionaries_search_hint))

            Spacer(Modifier.height(13.dp))

            Text(
                text = stringResource(R.string.dictionaries_my),
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
                title = stringResource(R.string.dictionaries_create),
                leadIcon = R.drawable.ic_add_square,
                secondaryIcon = R.drawable.ic_chevron_right_2,
            )

            Spacer(Modifier.height(13.dp))

            SecondaryCard(
                onClick = {},
                title = stringResource(R.string.dictionaries_own_words),
                subtitle = stringResource(R.string.words_count, 89),
                leadIcon = R.drawable.ic_alarmclock_1,
                trailingContent = {
                    Text(
                        text = stringResource(R.string.progress_percent, 89),
                        modifier = Modifier
                            .padding(end = 8.dp),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            )

            Spacer(Modifier.height(28.dp))

            Text(
                text = stringResource(R.string.dictionaries_added),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(8.dp))

            SecondaryCard(
                onClick = {},
                title = stringResource(R.string.dictionaries_own_words),
                subtitle = stringResource(R.string.words_count, 89),
                leadIcon = R.drawable.ic_alarmclock_1,
                trailingContent = {
                    Text(
                        text = stringResource(R.string.progress_percent, 27),
                        modifier = Modifier
                            .padding(end = 8.dp),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            )

            Spacer(Modifier.height(13.dp))

            SecondaryCard(
                onClick = {},
                title = stringResource(R.string.dictionaries_own_words),
                subtitle = stringResource(R.string.words_count, 60),
                leadIcon = R.drawable.ic_alarmclock_1,
                trailingContent = {
                    Text(
                        text = stringResource(R.string.progress_percent, 67),
                        modifier = Modifier
                            .padding(end = 8.dp),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            )

            Spacer(Modifier.height(13.dp))

            SecondaryCard(
                onClick = {},
                title = stringResource(R.string.dictionaries_own_words),
                subtitle = stringResource(R.string.words_count, 130),
                leadIcon = R.drawable.ic_alarmclock_1,
                trailingContent = {
                    Text(
                        text = stringResource(R.string.progress_percent, 66),
                        modifier = Modifier
                            .padding(end = 8.dp),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun DictionaryMainScreenPreview() {
    RelexyTheme() {
        DictionaryMainScreen()
    }
}