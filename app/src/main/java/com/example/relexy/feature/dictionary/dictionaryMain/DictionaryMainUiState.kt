package com.example.relexy.feature.dictionary.dictionaryMain

import com.example.relexy.domain.model.DictionaryListItem

data class DictionaryMainUiState(
    val searchQuery: String = "",
    val ownDictionaries: List<DictionaryListItem> = emptyList(),
    val addedDictionaries: List<DictionaryListItem> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)