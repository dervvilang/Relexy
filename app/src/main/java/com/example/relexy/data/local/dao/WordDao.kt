package com.example.relexy.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.relexy.data.local.entity.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Query(
        """
        SELECT * FROM words
        WHERE dictionaryId = :dictionaryId
        AND isDeleted = 0
        ORDER BY createdAt DESC
        """
    )
    fun observeWordsByDictionaryId(dictionaryId: String): Flow<List<WordEntity>> // список слов внутри конкретного словаря

    @Query(
        """
        SELECT * FROM words
        WHERE id = :wordId
        LIMIT 1
        """
    )
    suspend fun getWordById(wordId: String): WordEntity? // разово получение одного слова

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: WordEntity) // вставка одного слова

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWords(words: List<WordEntity>) // массовая вставка

    @Update
    suspend fun updateWord(word: WordEntity) // обновление слова целиком

    @Query(
        """
        UPDATE words
        SET isDeleted = 1,
            updatedAt = :updatedAt
        WHERE id = :wordId
        """
    )
    suspend fun softDeleteWord(
        wordId: String,
        updatedAt: Long
    )
    // мягкое удаление слова

    @Query(
        """
        DELETE FROM words
        WHERE id = :wordId
        """
    )
    suspend fun hardDeleteWordById(wordId: String) // полное физическое удаление слова

    @Query(
        """
        DELETE FROM words
        WHERE dictionaryId = :dictionaryId
        """
    )
    suspend fun hardDeleteWordsByDictionaryId(dictionaryId: String) // полная очистка словаря

    @Query(
        """
        SELECT * FROM words
        WHERE dictionaryId = :dictionaryId
        AND isDeleted = 0
        AND (
            originalText LIKE '%' || :query || '%'
            OR translationText LIKE '%' || :query || '%'
        )
        ORDER BY originalText ASC
        """
    )
    fun searchWordsInDictionary(
        dictionaryId: String,
        query: String
    ): Flow<List<WordEntity>>
    // поиск по слову и переводу внутри одного словаря

    @Query(
        """
        SELECT COUNT(*) FROM words
        WHERE dictionaryId = :dictionaryId
        AND isDeleted = 0
        """
    )
    suspend fun countWordsByDictionaryId(dictionaryId: String): Int // количество слов в словаре
}