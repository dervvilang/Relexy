package com.example.relexy.feature.dictionary.dictionaryMain

import android.content.Context
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.relexy.R
import com.example.relexy.core.ui.components.SecondaryCard
import com.example.relexy.core.ui.components.TertiaryCard
import com.example.relexy.core.ui.components.textFields.SearchTextField
import com.example.relexy.core.ui.theme.RelexyTheme
import com.example.relexy.data.local.entity.DictionaryOwnerType
import com.example.relexy.domain.model.DictionaryListItem

@Composable
fun DictionaryMainScreen(
    uiState: DictionaryMainUiState,
    onSearchQueryChange: (String) -> Unit,
    onGoToDictionaryScreen: (String) -> Unit,
    onCreateDictionary: () -> Unit,
    onImportClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .padding(horizontal = 16.dp)
            .pointerInput(Unit) { detectTapGestures { focusManager.clearFocus() } },
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    TextButton(
                        onClick = onImportClick,
                        contentPadding = PaddingValues(0.dp)
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

            item {
                SearchTextField(
                    value = uiState.searchQuery,
                    onValueChange = onSearchQueryChange,
                    placeholder = stringResource(R.string.dictionaries_search_hint)
                )
            }

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
                    onClick = onCreateDictionary,
                    title = stringResource(R.string.dictionaries_create),
                    leadIcon = R.drawable.ic_add_square,
                    secondaryIcon = R.drawable.ic_chevron_right_2,
                )
            }

            item { Spacer(Modifier.height(8.dp)) }

            items(
                items = uiState.ownDictionaries,
                key = { dictionary -> dictionary.id }
            ) { dictionary ->
                SecondaryCard(
                    onClick = { onGoToDictionaryScreen(dictionary.id) },
                    title = dictionary.title,
                    subtitle = stringResource(R.string.words_count, dictionary.wordCount),
                    leadIcon = dictionary.iconKey.toDictionaryIconRes(context),
                    trailingContent = {
                        Text(
                            text = stringResource(
                                R.string.progress_percent,
                                dictionary.masteredPercent
                            ),
                            modifier = Modifier.padding(end = 8.dp),
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                )
                Spacer(Modifier.height(8.dp))
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

            items(
                items = uiState.addedDictionaries,
                key = { dictionary -> dictionary.id }
            ) { dictionary ->
                SecondaryCard(
                    onClick = { onGoToDictionaryScreen(dictionary.id) },
                    title = dictionary.title,
                    subtitle = stringResource(R.string.words_count, dictionary.wordCount),
                    leadIcon = dictionary.iconKey.toDictionaryIconRes(context),
                    trailingContent = {
                        Text(
                            text = stringResource(
                                R.string.progress_percent,
                                dictionary.masteredPercent
                            ),
                            modifier = Modifier.padding(end = 8.dp),
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                )
                Spacer(Modifier.height(8.dp))
            }

            item { Spacer(Modifier.height(100.dp)) }
        }
    }
}

private fun String.toDictionaryIconRes(context: Context): Int {
    val resId = context.resources.getIdentifier(
        this,
        "drawable",
        context.packageName
    )
    return if (resId != 0) resId else R.drawable.ic_bluebook
}

@Preview
@Composable
private fun DictionaryMainScreenPreview() {
    RelexyTheme {
        DictionaryMainScreen(
            uiState = DictionaryMainUiState(
                searchQuery = "",
                ownDictionaries = listOf(
                    DictionaryListItem(
                        id = "1",
                        title = "Свои слова",
                        iconKey = "ic_for_dictionaries_books",
                        ownerType = DictionaryOwnerType.OWNED,
                        wordCount = 114,
                        masteredPercent = 71
                    )
                ),
                addedDictionaries = listOf(
                    DictionaryListItem(
                        id = "2",
                        title = "Путешествия",
                        iconKey = "ic_for_dictionaries_airplane",
                        ownerType = DictionaryOwnerType.ADDED,
                        wordCount = 143,
                        masteredPercent = 64
                    )
                ),
                isLoading = false,
                errorMessage = null
            ),
            onSearchQueryChange = {},
            onGoToDictionaryScreen = {},
            onCreateDictionary = {},
            onImportClick = {}
        )
    }
}