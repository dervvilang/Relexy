package com.example.relexy.feature.dictionary.dictionaryEditor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.relexy.domain.repository.DictionaryRepository

class DictionaryEditorViewModelFactory(
    private val dictionaryId: String?,
    private val dictionaryRepository: DictionaryRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DictionaryEditorViewModel::class.java)) {
            return DictionaryEditorViewModel(
                dictionaryId = dictionaryId,
                dictionaryRepository = dictionaryRepository
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}