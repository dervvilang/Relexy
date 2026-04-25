package com.example.relexy.feature.dictionary.dictionaryMain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.relexy.app.di.appContainer

@Composable
fun DictionaryMainRoute(
    onGoToDictionaryScreen: (String) -> Unit,
    onGoToDictionaryEditorScreen: (String?) -> Unit,
    onImportClick: () -> Unit = {}
) {
    val context = LocalContext.current

    val factory = remember(context) {
        DictionaryMainViewModelFactory(
            dictionaryRepository = context.appContainer.dictionaryRepository
        )
    }

    val viewModel: DictionaryMainViewModel = viewModel(factory = factory)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DictionaryMainScreen(
        uiState = uiState,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        onGoToDictionaryScreen = onGoToDictionaryScreen,
        onCreateDictionary = { onGoToDictionaryEditorScreen(null) },
        onImportClick = onImportClick
    )
}