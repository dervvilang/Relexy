package com.example.relexy.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.relexy.data.local.entity.DictionaryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DictionaryDao {

    @Query(
        """
        SELECT * FROM dictionaries
        WHERE isDeleted = 0
        ORDER BY updatedAt DESC
        """
    )
    fun observeAllDictionaries(): Flow<List<DictionaryEntity>> // наблюдение всех не удаленных словарей

    @Query(
        """
        SELECT * FROM dictionaries
        WHERE ownerType = :ownerType
        AND isDeleted = 0
        ORDER BY updatedAt DESC
        """
    )
    fun observeDictionariesByOwnerType(ownerType: String): Flow<List<DictionaryEntity>> // наблюдение словарей по типу владения

    @Query(
        """
        SELECT * FROM dictionaries
        WHERE id = :dictionaryId
        LIMIT 1
        """
    )
    suspend fun getDictionaryById(dictionaryId: String): DictionaryEntity? // получение словаря по id

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDictionary(dictionary: DictionaryEntity) // создание словаря или замена существующего, если id уже есть.

    @Update
    suspend fun updateDictionary(dictionary: DictionaryEntity) // полное обновление словаря

    /*@Query(
        """
        UPDATE dictionaries
        SET isDeleted = 1,
            updatedAt = :updatedAt
        WHERE id = :dictionaryId
        """
    )
    suspend fun softDeleteDictionary(
        dictionaryId: String,
        updatedAt: Long
    )
    // мягкое удаление словаря*/

    @Query(
        """
        DELETE FROM dictionaries
        WHERE id = :dictionaryId
        """
    )
    suspend fun hardDeleteDictionaryById(dictionaryId: String) // полное удаление словаря
}