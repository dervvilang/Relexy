package com.example.relexy.feature.dictionary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.relexy.domain.repository.DictionaryRepository
import com.example.relexy.domain.repository.WordRepository

class DictionaryViewModelFactory(
    private val dictionaryId: String,
    private val dictionaryRepository: DictionaryRepository,
    private val wordRepository: WordRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DictionaryViewModel::class.java)) {
            return DictionaryViewModel(
                dictionaryId = dictionaryId,
                dictionaryRepository = dictionaryRepository,
                wordRepository = wordRepository
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}