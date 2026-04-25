package com.example.relexy.feature.dictionary.dictionaryEditor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.relexy.domain.repository.DictionaryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DictionaryEditorViewModel(
    private val dictionaryId: String?,
    private val dictionaryRepository: DictionaryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        DictionaryEditorUiState(
            dictionaryId = dictionaryId,
            isEditMode = dictionaryId != null,
            isLoading = dictionaryId != null
        )
    )
    val uiState: StateFlow<DictionaryEditorUiState> = _uiState.asStateFlow()

    init {
        if (dictionaryId != null) {
            loadDictionary(dictionaryId)
        }
    }

    fun onTitleChange(newTitle: String) {
        _uiState.update { current ->
            current.copy(
                title = newTitle,
                titleError = null,
                errorMessage = null
            )
        }
    }

    fun onDescriptionChange(newDescription: String) {
        _uiState.update { current ->
            current.copy(
                description = newDescription,
                errorMessage = null
            )
        }
    }

    fun onIconSelected(iconKey: String) {
        _uiState.update { current ->
            current.copy(
                selectedIconKey = iconKey,
                errorMessage = null
            )
        }
    }

    fun onSaveClick() {
        val currentState = _uiState.value
        val trimmedTitle = currentState.title.trim()
        val trimmedDescription = currentState.description.trim().ifBlank { "" }

        if (trimmedTitle.isBlank()) {
            _uiState.update { current ->
                current.copy(
                    titleError = "Введите название словаря"
                )
            }
            return
        }

        viewModelScope.launch {
            _uiState.update { current ->
                current.copy(
                    isSaving = true,
                    errorMessage = null,
                    titleError = null
                )
            }

            try {
                val resultDictionaryId = if (currentState.isEditMode) {
                    dictionaryRepository.updateDictionary(
                        dictionaryId = requireNotNull(currentState.dictionaryId),
                        title = trimmedTitle,
                        description = trimmedDescription.ifBlank { null },
                        iconKey = currentState.selectedIconKey
                    )
                    requireNotNull(currentState.dictionaryId)
                } else {
                    dictionaryRepository.createDictionary(
                        title = trimmedTitle,
                        description = trimmedDescription.ifBlank { null },
                        iconKey = currentState.selectedIconKey
                    )
                }

                _uiState.update { current ->
                    current.copy(
                        isSaving = false,
                        savedDictionaryId = resultDictionaryId
                    )
                }
            } catch (t: Throwable) {
                _uiState.update { current ->
                    current.copy(
                        isSaving = false,
                        errorMessage = t.message ?: "Не удалось сохранить словарь"
                    )
                }
            }
        }
    }

    fun consumeSaveResult() {
        _uiState.update { current ->
            current.copy(savedDictionaryId = null)
        }
    }

    private fun loadDictionary(dictionaryId: String) {
        viewModelScope.launch {
            _uiState.update { current ->
                current.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            try {
                val dictionary = dictionaryRepository.getDictionaryDetails(dictionaryId)

                if (dictionary == null) {
                    _uiState.update { current ->
                        current.copy(
                            isLoading = false,
                            errorMessage = "Словарь не найден"
                        )
                    }
                    return@launch
                }

                _uiState.update { current ->
                    current.copy(
                        dictionaryId = dictionary.id,
                        title = dictionary.title,
                        description = dictionary.description.orEmpty(),
                        selectedIconKey = dictionary.iconKey,
                        isEditMode = true,
                        isLoading = false,
                        errorMessage = null
                    )
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
}