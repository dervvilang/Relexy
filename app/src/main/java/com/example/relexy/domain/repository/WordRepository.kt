package com.example.relexy.domain.repository

import com.example.relexy.data.local.entity.WordDifficulty
import com.example.relexy.domain.model.WordDetails
import com.example.relexy.domain.model.WordExampleData
import com.example.relexy.domain.model.WordListItem
import kotlinx.coroutines.flow.Flow

interface WordRepository {

    // наблюдение за списком слов внутри конкретного словаря
    fun observeWordsInDictionary(dictionaryId: String): Flow<List<WordListItem>>

    // поиск слов внутри одного словаря
    fun searchWordsInDictionary(
        dictionaryId: String,
        query: String
    ): Flow<List<WordListItem>>

    // детальная информация об одном слове
    suspend fun getWordDetails(wordId: String): WordDetails?

    // создание нового слова в словаре
    suspend fun createWord(
        dictionaryId: String,
        originalText: String,
        translationText: String,
        transcription: String?,
        imageLocalUri: String?,
        imageRemoteUrl: String?,
        examples: List<WordExampleData>
    ): String

    // обновление существующего слова
    suspend fun updateWord(
        wordId: String,
        originalText: String,
        translationText: String,
        transcription: String?,
        imageLocalUri: String?,
        imageRemoteUrl: String?,
        examples: List<WordExampleData>
    )

    // полное удаление слова
    suspend fun deleteWord(wordId: String)

    // ручное изменение сложности слова
    suspend fun updateWordDifficulty(
        wordId: String,
        difficulty: WordDifficulty
    )

    // ручной перевод слова в NEW
    suspend fun resetWordProgress(wordId: String)

    // ручной перевод слова в MASTERED
    suspend fun markWordAsMastered(wordId: String)

    // ручной перевод слова в LEARNING
    suspend fun startWordLearning(
        wordId: String,
        startedAtMillis: Long
    )

    // копирование слова в другой словарь пользователя
    suspend fun copyWordToDictionary(
        wordId: String,
        targetDictionaryId: String
    ): String
}