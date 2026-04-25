package com.example.relexy.feature.dictionary.dictionaryMain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.relexy.domain.model.DictionaryListItem
import com.example.relexy.domain.repository.DictionaryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class DictionaryMainViewModel(
    private val dictionaryRepository: DictionaryRepository
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")

    val uiState: StateFlow<DictionaryMainUiState> = combine(
        dictionaryRepository.observeOwnDictionaries(),
        dictionaryRepository.observeAddedDictionaries(),
        searchQuery
    ) { ownDictionaries, addedDictionaries, query ->

        DictionaryMainUiState(
            searchQuery = query,
            ownDictionaries = ownDictionaries.filterByQuery(query),
            addedDictionaries = addedDictionaries.filterByQuery(query),
            isLoading = false,
            errorMessage = null
        )
    }.catch { throwable ->
        emit(
            DictionaryMainUiState(
                searchQuery = searchQuery.value,
                ownDictionaries = emptyList(),
                addedDictionaries = emptyList(),
                isLoading = false,
                errorMessage = throwable.message ?: "Unknown error"
            )
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = DictionaryMainUiState()
    )

    fun onSearchQueryChange(newQuery: String) {
        searchQuery.update { newQuery }
    }

    private fun List<DictionaryListItem>.filterByQuery(query: String): List<DictionaryListItem> {
        if (query.isBlank()) return this

        return filter { dictionary ->
            dictionary.title.contains(query.trim(), ignoreCase = true)
        }
    }
}