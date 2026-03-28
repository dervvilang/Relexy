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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.example.relexy.domain.model.Dictionary

@Composable
fun DictionaryMainScreen() {
    val focusManager = LocalFocusManager.current

    val dictionaries = listOf(
        Dictionary("0", "Свои слова", R.drawable.ic_bluebook, 114, 71, true),
        Dictionary("1", "Фразовые глаголы", R.drawable.ic_ledger, 165, 47, false),
        Dictionary("2", "Путешествия", R.drawable.ic_airplane, 143, 64, false),
        Dictionary("3", "Животные", R.drawable.ic_wolfface, 96, 55, false),
        Dictionary("4", "Животные", R.drawable.ic_wolfface, 43, 25, false),
        Dictionary("5", "Животные", R.drawable.ic_wolfface, 44, 76, false),
        Dictionary("6", "Животные", R.drawable.ic_wolfface, 87, 51, false),
        Dictionary("7", "Животные", R.drawable.ic_wolfface, 28, 93, false),
    )

    val myDictionaries = dictionaries.filter { it.isOwnedByUser }
    val addedDictionaries = dictionaries.filter { !it.isOwnedByUser }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .padding(horizontal = 16.dp)
            .pointerInput(Unit) { detectTapGestures { focusManager.clearFocus() } },
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(R.string.nav_dictionaries),
                        modifier = Modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.displayLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    TextButton(
                        onClick = {}, contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.dictionaries_import),
                            modifier = Modifier.padding(end = 8.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            item { Spacer(Modifier.height(13.dp)) }

            item { SearchCard(stringResource(R.string.dictionaries_search_hint)) }

            item { Spacer(Modifier.height(13.dp)) }

            item {
                Text(
                    text = stringResource(R.string.dictionaries_my),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                TertiaryCard(
                    onClick = {},
                    title = stringResource(R.string.dictionaries_create),
                    leadIcon = R.drawable.ic_add_square,
                    secondaryIcon = R.drawable.ic_chevron_right_2,
                )
            }

            item { Spacer(Modifier.height(8.dp)) }

            if (myDictionaries.isNotEmpty()) {
                itemsIndexed(
                    items = myDictionaries,
                    key = { _, dictionary -> dictionary.id }
                ) { index, dictionary ->
                    SecondaryCard(
                        onClick = {},
                        title = dictionary.title,
                        subtitle = stringResource(R.string.words_count, dictionary.wordCount),
                        leadIcon = dictionary.leadIcon,
                        trailingContent = {
                            Text(
                                text = stringResource(R.string.progress_percent, dictionary.progress),
                                modifier = Modifier.padding(end = 8.dp),
                                textAlign = TextAlign.Start,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }

            item {
                Spacer(Modifier.height(22.dp))

                Text(
                    text = stringResource(R.string.dictionaries_added),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            item { Spacer(Modifier.height(8.dp)) }

            if (addedDictionaries.isNotEmpty()) {
                itemsIndexed(
                    items = addedDictionaries,
                    key = { _, dictionary -> dictionary.id }
                ) { index, dictionary ->
                    SecondaryCard(
                        onClick = {},
                        title = dictionary.title,
                        subtitle = stringResource(R.string.words_count, dictionary.wordCount),
                        leadIcon = dictionary.leadIcon,
                        trailingContent = {
                            Text(
                                text = stringResource(R.string.progress_percent, dictionary.progress),
                                modifier = Modifier.padding(end = 8.dp),
                                textAlign = TextAlign.Start,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }

            item { Spacer(Modifier.height(100.dp)) }
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




/*Column(
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.nav_dictionaries),
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        TextButton(
            onClick = {}, contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = stringResource(R.string.dictionaries_import),
                modifier = Modifier.padding(end = 8.dp),
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
                modifier = Modifier.padding(end = 8.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        })

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
                modifier = Modifier.padding(end = 8.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        })

    Spacer(Modifier.height(13.dp))

    SecondaryCard(
        onClick = {},
        title = stringResource(R.string.dictionaries_own_words),
        subtitle = stringResource(R.string.words_count, 60),
        leadIcon = R.drawable.ic_alarmclock_1,
        trailingContent = {
            Text(
                text = stringResource(R.string.progress_percent, 67),
                modifier = Modifier.padding(end = 8.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        })

    Spacer(Modifier.height(13.dp))

    SecondaryCard(
        onClick = {},
        title = stringResource(R.string.dictionaries_own_words),
        subtitle = stringResource(R.string.words_count, 130),
        leadIcon = R.drawable.ic_alarmclock_1,
        trailingContent = {
            Text(
                text = stringResource(R.string.progress_percent, 66),
                modifier = Modifier.padding(end = 8.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    )
}*/