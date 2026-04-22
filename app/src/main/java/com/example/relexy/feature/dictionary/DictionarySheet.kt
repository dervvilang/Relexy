package com.example.relexy.feature.dictionary

sealed interface DictionarySheet {
    data object DictionaryMenu: DictionarySheet
    data class WordMenu(val wordId: String): DictionarySheet
}