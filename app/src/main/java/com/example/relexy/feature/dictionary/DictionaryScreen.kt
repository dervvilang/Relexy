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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.relexy.R
import com.example.relexy.core.ui.components.TopBar
import com.example.relexy.core.ui.components.WordCard
import com.example.relexy.core.ui.components.buttons.AddRoundButton
import com.example.relexy.core.ui.components.buttons.SortModeButton
import com.example.relexy.core.ui.theme.RelexyTheme
import com.example.relexy.data.local.entity.WordStatus
import com.example.relexy.domain.model.WordListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryScreen(
    uiState: DictionaryUiState,
    onBackClick: () -> Unit,
    onSortModeClick: () -> Unit,
    onEditDictionaryClick: () -> Unit,
    onResetDictionaryProgressClick: () -> Unit,
    onClearDictionaryClick: () -> Unit,
    onDeleteDictionaryClick: () -> Unit,
    onAddWordClick: () -> Unit,
    onEditWordClick: (String) -> Unit,
    onResetWordProgressClick: (String) -> Unit,
    onMarkWordAsMasteredClick: (String) -> Unit,
    onDeleteWordClick: (String) -> Unit
) {
    var currentSheet by remember {
        mutableStateOf<DictionarySheet?>(null)
    }
    val sheetState = rememberModalBottomSheetState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopBar(
                title = uiState.title.ifBlank { "Словарь" },
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
                },
                rightContent = {
                    IconButton(
                        onClick = { currentSheet = DictionarySheet.DictionaryMenu },
                        modifier = Modifier.size(50.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_more_circle_1),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.End
            ) {
                SortModeButton(
                    sortMode = uiState.sortMode,
                    onClick = onSortModeClick
                )
            }

            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                uiState.errorMessage != null && uiState.words.isEmpty() -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = uiState.errorMessage,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                uiState.words.isEmpty() -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "В этом словаре пока нет слов",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(
                            items = uiState.words,
                            key = { word -> word.id }
                        ) { word ->
                            WordCard(
                                item = word,
                                onClick = {
                                    currentSheet = DictionarySheet.WordMenu(word)
                                }
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        item { Spacer(Modifier.height(80.dp)) }
                    }
                }
            }
        }

        currentSheet?.let { sheet ->
            ModalBottomSheet(
                onDismissRequest = {
                    currentSheet = null
                },
                sheetState = sheetState,
                containerColor = MaterialTheme.colorScheme.background,
                dragHandle = {
                    BottomSheetDefaults.DragHandle(
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            ) {
                when (sheet) {
                    DictionarySheet.DictionaryMenu -> {
                        DictionaryMenuBottomSheetContent(
                            onEditDictionary = {
                                currentSheet = null
                                onEditDictionaryClick()
                            },
                            onResetDictionaryProgress = {
                                currentSheet = null
                                onResetDictionaryProgressClick()
                            },
                            onClearDictionary = {
                                currentSheet = null
                                onClearDictionaryClick()
                            },
                            onImportNewWords = {
                                currentSheet = null
                            },
                            onPublishDictionaryToProfile = {
                                currentSheet = null
                            },
                            onShareDictionary = {
                                currentSheet = null
                            },
                            onDeleteDictionary = {
                                currentSheet = null
                                onDeleteDictionaryClick()
                            }
                        )
                    }

                    is DictionarySheet.WordMenu -> {
                        WordMenuBottomSheetContent(
                            wordId = sheet.word.id,
                            onResetWordProgress = {
                                currentSheet = null
                                onResetWordProgressClick(sheet.word.id)
                            },
                            onMarkWordAsMastered = {
                                currentSheet = null
                                onMarkWordAsMasteredClick(sheet.word.id)
                            },
                            onEditWord = {
                                currentSheet = null
                                onEditWordClick(sheet.word.id)
                            },
                            onDeleteWord = {
                                currentSheet = null
                                onDeleteWordClick(sheet.word.id)
                            }
                        )
                    }
                }
            }
        }

        if (uiState.isOwnedByUser) {
            AddRoundButton(
                onClick = onAddWordClick,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

@Preview
@Composable
private fun DictionaryScreenPreview() {
    RelexyTheme {
        DictionaryScreen(
            uiState = DictionaryUiState(
                dictionaryId = "dictionary_1",
                title = "Мой словарь",
                isOwnedByUser = true,
                words = listOf(
                    WordListItem(
                        id = "1",
                        originalText = "Achieve",
                        translationText = "Достигать",
                        status = WordStatus.NEW
                    ),
                    WordListItem(
                        id = "2",
                        originalText = "Confidence",
                        translationText = "Уверенность",
                        status = WordStatus.LEARNING
                    )
                ),
                sortMode = DictionarySortMode.BY_STATUS,
                isLoading = false,
                errorMessage = null
            ),
            onBackClick = {},
            onSortModeClick = {},
            onEditDictionaryClick = {},
            onResetDictionaryProgressClick = {},
            onClearDictionaryClick = {},
            onDeleteDictionaryClick = {},
            onAddWordClick = {},
            onEditWordClick = {},
            onResetWordProgressClick = {},
            onMarkWordAsMasteredClick = {},
            onDeleteWordClick = {}
        )
    }
}