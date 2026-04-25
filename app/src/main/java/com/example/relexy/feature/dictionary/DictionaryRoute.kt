package com.example.relexy.feature.dictionary

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.relexy.app.di.appContainer

@Composable
fun DictionaryRoute(
    dictionaryId: String,
    onBackClick: () -> Unit,
    onGoToDictionaryEditorScreen: (String?) -> Unit,
    onGoToWordEditorScreen: (String, String?) -> Unit
) {
    val context = LocalContext.current

    val factory = remember(context, dictionaryId) {
        DictionaryViewModelFactory(
            dictionaryId = dictionaryId,
            dictionaryRepository = context.appContainer.dictionaryRepository,
            wordRepository = context.appContainer.wordRepository
        )
    }

    val viewModel: DictionaryViewModel = viewModel(factory = factory)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.shouldCloseScreen) {
        if (uiState.shouldCloseScreen) {
            onBackClick()
            viewModel.consumeCloseScreenRequest()
        }
    }

    DictionaryScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        onSortModeClick = viewModel::onSortModeClick,
        onEditDictionaryClick = {
            onGoToDictionaryEditorScreen(dictionaryId)
        },
        onResetDictionaryProgressClick = viewModel::onResetDictionaryProgressClick,
        onClearDictionaryClick = viewModel::onClearDictionaryClick,
        onDeleteDictionaryClick = viewModel::onDeleteDictionaryClick,
        onAddWordClick = {
            onGoToWordEditorScreen(dictionaryId, null)
        },
        onEditWordClick = { wordId ->
            onGoToWordEditorScreen(dictionaryId, wordId)
        },
        onResetWordProgressClick = viewModel::onResetWordProgressClick,
        onMarkWordAsMasteredClick = viewModel::onMarkWordAsMasteredClick,
        onDeleteWordClick = viewModel::onDeleteWordClick
    )
}