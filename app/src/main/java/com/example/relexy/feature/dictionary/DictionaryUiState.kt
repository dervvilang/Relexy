package com.example.relexy.feature.dictionary

import com.example.relexy.domain.model.WordListItem

data class DictionaryUiState(
    val dictionaryId: String,
    val title: String = "",
    val description: String? = null,
    val isOwnedByUser: Boolean = true,
    val words: List<WordListItem> = emptyList(),
    val sortMode: DictionarySortMode = DictionarySortMode.BY_STATUS,
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val shouldCloseScreen: Boolean = false
)