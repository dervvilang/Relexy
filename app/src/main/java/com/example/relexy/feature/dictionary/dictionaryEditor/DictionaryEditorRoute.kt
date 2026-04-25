package com.example.relexy.feature.dictionary.dictionaryEditor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.relexy.app.di.appContainer

@Composable
fun DictionaryEditorRoute(
    dictionaryId: String?,
    onBackClick: () -> Unit,
    onOpenDictionary: (String) -> Unit
) {
    val context = LocalContext.current

    val factory = remember(context, dictionaryId) {
        DictionaryEditorViewModelFactory(
            dictionaryId = dictionaryId,
            dictionaryRepository = context.appContainer.dictionaryRepository
        )
    }

    val viewModel: DictionaryEditorViewModel = viewModel(factory = factory)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.savedDictionaryId) {
        val savedId = uiState.savedDictionaryId ?: return@LaunchedEffect

        if (dictionaryId == null) {
            onOpenDictionary(savedId)
        } else {
            onBackClick()
        }

        viewModel.consumeSaveResult()
    }

    DictionaryEditorScreen(
        uiState = uiState,
        onTitleChange = viewModel::onTitleChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onIconSelected = viewModel::onIconSelected,
        onSaveClick = viewModel::onSaveClick,
        onBackClick = onBackClick
    )
}