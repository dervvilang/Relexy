package com.example.relexy.feature.dictionary.dictionaryEditor

data class DictionaryEditorUiState(
    val dictionaryId: String? = null,
    val title: String = "",
    val description: String = "",
    val selectedIconKey: String = DEFAULT_DICTIONARY_ICON_KEY,
    val isEditMode: Boolean = false,
    val isLoading: Boolean = false,
    val isSaving: Boolean = false,
    val titleError: String? = null,
    val errorMessage: String? = null,
    val savedDictionaryId: String? = null
)

const val DEFAULT_DICTIONARY_ICON_KEY = "ic_for_dictionaries_acorn"