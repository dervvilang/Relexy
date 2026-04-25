package com.example.relexy.feature.dictionary

import com.example.relexy.domain.model.WordListItem

sealed interface DictionarySheet {
    data object DictionaryMenu : DictionarySheet
    data class WordMenu(val word: WordListItem) : DictionarySheet
}