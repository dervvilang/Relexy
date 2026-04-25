package com.example.relexy.feature.dictionary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.relexy.data.local.entity.DictionaryOwnerType
import com.example.relexy.data.local.entity.WordStatus
import com.example.relexy.domain.model.DictionaryDetails
import com.example.relexy.domain.model.WordListItem
import com.example.relexy.domain.repository.DictionaryRepository
import com.example.relexy.domain.repository.WordRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DictionaryViewModel(
    private val dictionaryId: String,
    private val dictionaryRepository: DictionaryRepository,
    private val wordRepository: WordRepository
) : ViewModel() {

    private val dictionaryDetails = MutableStateFlow<DictionaryDetails?>(null)
    private val sortMode = MutableStateFlow(DictionarySortMode.BY_STATUS)

    private val _uiState = MutableStateFlow(
        DictionaryUiState(
            dictionaryId = dictionaryId,
            isLoading = true
        )
    )
    val uiState: StateFlow<DictionaryUiState> = _uiState.asStateFlow()

    init {
        observeDictionaryContent()
        loadDictionary()
    }

    fun onSortModeClick() {
        sortMode.update { current -> current.next() }
    }

    fun onResetDictionaryProgressClick() {
        viewModelScope.launch {
            try {
                dictionaryRepository.resetDictionaryProgress(dictionaryId)
            } catch (t: Throwable) {
                _uiState.update { current ->
                    current.copy(
                        errorMessage = t.message ?: "Не удалось сбросить прогресс словаря"
                    )
                }
            }
        }
    }

    fun onClearDictionaryClick() {
        viewModelScope.launch {
            try {
                dictionaryRepository.clearDictionary(dictionaryId)
            } catch (t: Throwable) {
                _uiState.update { current ->
                    current.copy(
                        errorMessage = t.message ?: "Не удалось очистить словарь"
                    )
                }
            }
        }
    }

    fun onDeleteDictionaryClick() {
        viewModelScope.launch {
            try {
                dictionaryRepository.deleteDictionary(dictionaryId)
                _uiState.update { current ->
                    current.copy(shouldCloseScreen = true)
                }
            } catch (t: Throwable) {
                _uiState.update { current ->
                    current.copy(
                        errorMessage = t.message ?: "Не удалось удалить словарь"
                    )
                }
            }
        }
    }

    fun onResetWordProgressClick(wordId: String) {
        viewModelScope.launch {
            try {
                wordRepository.resetWordProgress(wordId)
            } catch (t: Throwable) {
                _uiState.update { current ->
                    current.copy(
                        errorMessage = t.message ?: "Не удалось сбросить прогресс слова"
                    )
                }
            }
        }
    }

    fun onMarkWordAsMasteredClick(wordId: String) {
        viewModelScope.launch {
            try {
                wordRepository.markWordAsMastered(wordId)
            } catch (t: Throwable) {
                _uiState.update { current ->
                    current.copy(
                        errorMessage = t.message ?: "Не удалось отметить слово как усвоенное"
                    )
                }
            }
        }
    }

    fun onDeleteWordClick(wordId: String) {
        viewModelScope.launch {
            try {
                wordRepository.deleteWord(wordId)
            } catch (t: Throwable) {
                _uiState.update { current ->
                    current.copy(
                        errorMessage = t.message ?: "Не удалось удалить слово"
                    )
                }
            }
        }
    }

    fun consumeCloseScreenRequest() {
        _uiState.update { current ->
            current.copy(shouldCloseScreen = false)
        }
    }

    private fun loadDictionary() {
        viewModelScope.launch {
            _uiState.update { current ->
                current.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            try {
                val details = dictionaryRepository.getDictionaryDetails(dictionaryId)

                if (details == null) {
                    _uiState.update { current ->
                        current.copy(
                            isLoading = false,
                            errorMessage = "Словарь не найден"
                        )
                    }
                } else {
                    dictionaryDetails.value = details
                    _uiState.update { current ->
                        current.copy(
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }
            } catch (t: Throwable) {
                _uiState.update { current ->
                    current.copy(
                        isLoading = false,
                        errorMessage = t.message ?: "Не удалось загрузить словарь"
                    )
                }
            }
        }
    }

    private fun observeDictionaryContent() {
        viewModelScope.launch {
            combine(
                dictionaryDetails,
                wordRepository.observeWordsInDictionary(dictionaryId),
                sortMode
            ) { details, words, currentSortMode ->
                Triple(
                    details,
                    words.sortedBySortMode(currentSortMode),
                    currentSortMode
                )
            }.collect { (details, sortedWords, currentSortMode) ->
                _uiState.update { current ->
                    current.copy(
                        title = details?.title.orEmpty(),
                        description = details?.description,
                        isOwnedByUser = details?.ownerType == DictionaryOwnerType.OWNED,
                        words = sortedWords,
                        sortMode = currentSortMode
                    )
                }
            }
        }
    }
}

private fun List<WordListItem>.sortedBySortMode(
    sortMode: DictionarySortMode
): List<WordListItem> {
    return when (sortMode) {
        DictionarySortMode.BY_STATUS -> sortedWith(
            compareBy<WordListItem> { it.status.toSortOrder() }
                .thenBy { it.originalText.lowercase() }
        )

        DictionarySortMode.BY_NOVELTY -> this

        DictionarySortMode.BY_ALPHABET -> sortedBy { it.originalText.lowercase() }
    }
}

private fun WordStatus.toSortOrder(): Int {
    return when (this) {
        WordStatus.NEW -> 0
        WordStatus.LEARNING -> 1
        WordStatus.MASTERED -> 2
    }
}