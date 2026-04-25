package com.example.relexy.feature.dictionary

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.relexy.R
import com.example.relexy.core.ui.components.OutlineCheckbox
import com.example.relexy.core.ui.components.SecondaryCard
import com.example.relexy.core.ui.components.TopBar
import com.example.relexy.core.ui.components.buttons.PrimaryButtonWithBlur
import com.example.relexy.core.ui.theme.RelexyTheme
import com.example.relexy.domain.roughModels.Dictionary

@Composable
fun DictionaryChooseScreen(
    onBackClick: () -> Unit
) {
    val dictionaries = listOf(
        Dictionary("0", "Свои слова", R.drawable.ic_bluebook, 114, 71, true),
        Dictionary("1", "Фразовые глаголы", R.drawable.ic_ledger, 165, 47, false),
        Dictionary("2", "Путешествия", R.drawable.ic_airplane, 143, 64, false),
        Dictionary("3", "Животные", R.drawable.ic_wolfface, 96, 55, false),
        Dictionary("4", "Животные", R.drawable.ic_wolfface, 43, 25, false),
        Dictionary("5", "Животные", R.drawable.ic_wolfface, 44, 76, false),
        Dictionary("6", "Животные", R.drawable.ic_wolfface, 87, 51, false),
        Dictionary("7", "Животные", R.drawable.ic_wolfface, 28, 93, false),
        Dictionary("8", "Животные", R.drawable.ic_wolfface, 87, 51, false),
        Dictionary("9", "Животные", R.drawable.ic_wolfface, 28, 93, false),
    )

    val myDictionaries = dictionaries.filter { it.isOwnedByUser }
    val addedDictionaries = dictionaries.filter { !it.isOwnedByUser }

    var selectedDictionaryIds by rememberSaveable {
        mutableStateOf(setOf<String>())
    }

    fun toggleDictionary(dictionaryId: String) {
        selectedDictionaryIds =
            if (dictionaryId in selectedDictionaryIds) {
                selectedDictionaryIds - dictionaryId
            } else {
                selectedDictionaryIds + dictionaryId
            }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(
                title = stringResource(R.string.select_dictionary_title),
                leftContent = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.size(50.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_arrow_right_circle),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
            )

            Spacer(Modifier.height(8.dp))

            LazyColumn(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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

                if (myDictionaries.isNotEmpty()) {
                    itemsIndexed(
                        items = myDictionaries,
                        key = { _, dictionary -> dictionary.id }
                    ) { index, dictionary ->
                        SecondaryCard(
                            onClick = { toggleDictionary(dictionary.id) },
                            title = dictionary.title,
                            subtitle = stringResource(R.string.words_count, dictionary.wordCount),
                            leadIcon = dictionary.leadIcon,
                            trailingContent = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                ) {
                                    Text(
                                        text = stringResource(
                                            R.string.progress_percent,
                                            dictionary.progress
                                        ),
                                        textAlign = TextAlign.Start,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    )

                                    OutlineCheckbox(
                                        checked = dictionary.id in selectedDictionaryIds,
                                        onCheckedChange = {
                                            toggleDictionary(dictionary.id)
                                        },
                                    )
                                }
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
                            onClick = { toggleDictionary(dictionary.id) },
                            title = dictionary.title,
                            subtitle = stringResource(R.string.words_count, dictionary.wordCount),
                            leadIcon = dictionary.leadIcon,
                            trailingContent = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                ) {
                                    Text(
                                        text = stringResource(
                                            R.string.progress_percent,
                                            dictionary.progress
                                        ),
                                        textAlign = TextAlign.Start,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    )

                                    OutlineCheckbox(
                                        checked = dictionary.id in selectedDictionaryIds,
                                        onCheckedChange = {
                                            toggleDictionary(dictionary.id)
                                        }
                                    )
                                }
                            }
                        )
                        Spacer(Modifier.height(8.dp))
                    }
                }

                item { Spacer(Modifier.height(110.dp)) }
            }
        }

        PrimaryButtonWithBlur(
            text = stringResource(R.string.action_save),
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .align(Alignment.BottomCenter)
        )
    }
}


@Preview
@Composable
fun DictionaryChooseScreenPreview() {
    RelexyTheme() {
        DictionaryChooseScreen(onBackClick = {})
    }
}
