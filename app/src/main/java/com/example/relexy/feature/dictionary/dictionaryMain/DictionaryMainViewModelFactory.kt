package com.example.relexy.feature.dictionary.dictionaryMain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.relexy.domain.repository.DictionaryRepository

class DictionaryMainViewModelFactory(
    private val dictionaryRepository: DictionaryRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DictionaryMainViewModel::class.java)) {
            return DictionaryMainViewModel(
                dictionaryRepository = dictionaryRepository
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}