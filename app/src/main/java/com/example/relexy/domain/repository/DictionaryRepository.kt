package com.example.relexy.domain.repository

import com.example.relexy.domain.model.DictionaryDetails
import com.example.relexy.domain.model.DictionaryListItem
import kotlinx.coroutines.flow.Flow

interface DictionaryRepository {

    // полный список словарей пользователя
    fun observeAllDictionaries(): Flow<List<DictionaryListItem>>

    // только свои словари
    fun observeOwnDictionaries(): Flow<List<DictionaryListItem>>

    // только добавленные словари
    fun observeAddedDictionaries(): Flow<List<DictionaryListItem>>

    // детальная информация об одном словаре
    suspend fun getDictionaryDetails(dictionaryId: String): DictionaryDetails?

    // создание нового словаря
    // возвращает id созданного словаря, чтобы потом можно было перейти на его экран
    suspend fun createDictionary(
        title: String,
        description: String?,
        iconKey: String
    ): String

    // обновление уже существующего словаря
    suspend fun updateDictionary(
        dictionaryId: String,
        title: String,
        description: String?,
        iconKey: String
    )

    // полное удаление словаря
    suspend fun deleteDictionary(dictionaryId: String)

    // очистка словаря без удаления самого словаря
    suspend fun clearDictionary(dictionaryId: String)

    // сброс прогресса по всем словам словаря
    suspend fun resetDictionaryProgress(dictionaryId: String)

    // отметить словарь как выбранный для обучения
    suspend fun selectDictionary(dictionaryId: String)

    // снять выбор словаря для обучения
    suspend fun unselectDictionary(dictionaryId: String)

    // если selected true -> выбрать словарь
    // если selected false -> снять выбор
    suspend fun setDictionarySelected(
        dictionaryId: String,
        selected: Boolean
    )

    // наблюдение за id выбранных словарей
    // для экрана выбора словарей: можно быстро проверять, отмечен словарь или нет
    fun observeSelectedDictionaryIds(): Flow<Set<String>>

    // разовый запрос выбранных словарей
    // для формирования очереди обучения/повторения
    suspend fun getSelectedDictionaryIds(): List<String>

    // очистить весь текущий набор выбранных словарей
    suspend fun clearSelectedDictionaries()
}