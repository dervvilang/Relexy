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
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.relexy.R
import com.example.relexy.core.ui.components.buttons.SortModeButton
import com.example.relexy.core.ui.components.TopBar
import com.example.relexy.core.ui.components.WordCard
import com.example.relexy.core.ui.components.buttons.AddRoundButton
import com.example.relexy.core.ui.components.buttons.next
import com.example.relexy.core.ui.theme.RelexyTheme
import com.example.relexy.domain.model.Word
import com.example.relexy.domain.model.WordStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryScreen(
    onBackClick: () -> Unit,
    onGoToWordEditorScreen: (String) -> Unit
) {
    var sortMode by rememberSaveable {
        mutableStateOf(DictionarySortMode.BY_STATUS)
    }

    var currentSheet by remember {
        mutableStateOf<DictionarySheet?>(null)
    }
    val sheetState = rememberModalBottomSheetState()

    val words = listOf(
        Word(
            id = "0",
            originalText = "Achieve",
            translationText = "Достигать",
            status = WordStatus.NEW
        ),
        Word(
            id = "1",
            originalText = "Confidence",
            translationText = "Уверенность",
            status = WordStatus.NEW
        ),
        Word(
            id = "2",
            originalText = "I go to the gym three times a week.",
            translationText = "Я хожу в зал три раза в неделю.",
            status = WordStatus.LEARNING
        ),
        Word(
            id = "3",
            originalText = "She is learning Kotlin because she wants to become an Android developer.",
            translationText = "Она изучает Kotlin, потому что хочет стать Android-разработчицей.",
            status = WordStatus.LEARNING
        ),
        Word(
            id = "4",
            originalText = "Improve",
            translationText = "Улучшать",
            status = WordStatus.LEARNING
        ),
        Word(
            id = "5",
            originalText = "This book helped me understand that small daily habits can change my life over time.",
            translationText = "Эта книга помогла мне понять, что маленькие ежедневные привычки могут со временем изменить мою жизнь.",
            status = WordStatus.MASTERED
        ),
        Word(
            id = "6",
            originalText = "Deadline",
            translationText = "Крайний срок",
            status = WordStatus.MASTERED
        ),
        Word(
            id = "7",
            originalText = "We should focus on progress, not perfection.",
            translationText = "Нам стоит сосредоточиться на прогрессе, а не на идеальности.",
            status = WordStatus.MASTERED
        ),
    )

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
                title = stringResource(R.string.dictionary_title_placeholder),
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
                    sortMode = sortMode,
                    onClick = {
                        sortMode = sortMode.next()
                    }
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                itemsIndexed(
                    items = words,
                    key = { _, word -> word.id }
                ) { _, word ->
                    WordCard(
                        item = word,
                        onClick = {
                            currentSheet = DictionarySheet.WordMenu(wordId = word.id)
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }

                item { Spacer(Modifier.height(80.dp)) }
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
                            onEditDictionary = {},
                            onResetDictionaryProgress = {},
                            onDeleteDictionary = {},
                            onClearDictionary = {},
                            onImportNewWords = {},
                            onPublishDictionaryToProfile = {},
                            onShareDictionary = {}
                        )
                    }

                    is DictionarySheet.WordMenu -> {
                        WordMenuBottomSheetContent(
                            wordId = sheet.wordId,
                            onResetWordProgress = {},
                            onMarkWordAsMastered = {},
                            onEditWord = { onGoToWordEditorScreen(sheet.wordId) },
                            onDeleteWord = {}
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(120.dp))

        AddRoundButton(
            onClick = {},
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview
@Composable
fun DictionaryScreenPreview() {
    RelexyTheme {
        DictionaryScreen(onBackClick = {}, {})
    }
}