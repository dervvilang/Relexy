package com.example.relexy.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.relexy.data.local.entity.WordExampleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordExampleDao {

    @Query(
        """
        SELECT * FROM word_examples
        WHERE wordId = :wordId
        ORDER BY position ASC
        """
    )
    fun observeExamplesByWordId(wordId: String): Flow<List<WordExampleEntity>> // наблюдение всех примеров конкретного слова

    @Query(
        """
        SELECT * FROM word_examples
        WHERE wordId = :wordId
        ORDER BY position ASC
        """
    )
    suspend fun getExamplesByWordId(wordId: String): List<WordExampleEntity> // получение всех примеров конкретного слова

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExample(example: WordExampleEntity) // добавление одного примера

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExamples(examples: List<WordExampleEntity>) // добавление сразу нескольких примеров

    @Update
    suspend fun updateExample(example: WordExampleEntity) // обновление одного примера

    @Query(
        """
        DELETE FROM word_examples
        WHERE id = :exampleId
        """
    )
    suspend fun deleteExampleById(exampleId: String) // удаление одного примера

    @Query(
        """
        DELETE FROM word_examples
        WHERE wordId = :wordId
        """
    )
    suspend fun deleteExamplesByWordId(wordId: String) // удаление всех примеров слова
}