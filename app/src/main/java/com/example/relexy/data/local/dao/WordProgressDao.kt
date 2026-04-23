package com.example.relexy.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.relexy.data.local.entity.WordProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordProgressDao {

    @Query(
        """
        SELECT * FROM word_progress
        WHERE wordId = :wordId
        LIMIT 1
        """
    )
    suspend fun getProgressByWordId(wordId: String): WordProgressEntity?  // получение прогресса конкретного слова

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(progress: WordProgressEntity) // вставка прогресса для одного слова

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgressList(progressList: List<WordProgressEntity>) // массовая вставка прогресса

    @Update
    suspend fun updateProgress(progress: WordProgressEntity) // обновление прогресса

    @Query(
        """
        SELECT * FROM word_progress
        WHERE dictionaryId = :dictionaryId
        """
    )
    fun observeProgressByDictionaryId(dictionaryId: String): Flow<List<WordProgressEntity>> // весь прогресс словаря

    @Query(
        """
        SELECT COUNT(*) FROM word_progress
        WHERE dictionaryId = :dictionaryId
        AND status = :status
        """
    )
    suspend fun countWordsByStatus(
        dictionaryId: String,
        status: String
    ): Int
    // сколько слов в словаре имеют конкретный статус

    @Query(
        """
        SELECT COUNT(*) FROM word_progress
        WHERE status = :status
        AND dictionaryId IN (:dictionaryIds)
        """
    )
    suspend fun countWordsByStatusInDictionaries(
        status: String,
        dictionaryIds: List<String>
    ): Int
    // считает количество слов нужного статуса сразу по нескольким словарям

    @Query(
        """
        SELECT * FROM word_progress
        WHERE status = :status
        AND dictionaryId IN (:dictionaryIds)
        ORDER BY firstSeenAt ASC
        """
    )
    suspend fun getProgressByStatusInDictionaries(
        status: String,
        dictionaryIds: List<String>
    ): List<WordProgressEntity>
    // получаем все слова определенного статуса по нескольким словарям

    @Query(
        """
        SELECT * FROM word_progress
        WHERE status = 'LEARNING'
        AND nextDueAt IS NOT NULL
        AND nextDueAt <= :now
        AND dictionaryId IN (:dictionaryIds)
        ORDER BY nextDueAt ASC
        """
    )
    suspend fun getDueLearningProgress(
        now: Long,
        dictionaryIds: List<String>
    ): List<WordProgressEntity>
    // очередь слов на повторение

    @Query(
        """
        SELECT COUNT(*) FROM word_progress
        WHERE status = 'LEARNING'
        AND nextDueAt IS NOT NULL
        AND nextDueAt <= :now
        AND dictionaryId IN (:dictionaryIds)
        """
    )
    suspend fun countDueLearningWords(
        now: Long,
        dictionaryIds: List<String>
    ): Int
    // сколько слов доступно к повторению прямо сейчас

    @Query(
        """
        UPDATE word_progress
        SET 
            status = 'NEW',
            difficulty = 'NORMAL',
            nextDueAt = NULL,
            currentIntervalDays = NULL,
            firstSeenAt = NULL,
            lastSeenAt = NULL,
            successStreak = 0,
            failStreak = 0,
            successCount = 0
        WHERE dictionaryId = :dictionaryId
        """
    )
    suspend fun resetProgressByDictionaryId(dictionaryId: String)
    // полный сброс прогресса по словарю (все становятся NEW)

    @Query(
        """
        DELETE FROM word_progress
        WHERE dictionaryId = :dictionaryId
        """
    )
    suspend fun deleteProgressByDictionaryId(dictionaryId: String)
    // полное удаление progress-записей словаря
}