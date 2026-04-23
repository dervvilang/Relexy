package com.example.relexy.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.relexy.data.local.entity.SelectedDictionaryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SelectedDictionaryDao {

    @Query(
        """
        SELECT * FROM selected_dictionaries
        ORDER BY dictionaryId ASC
        """
    )
    fun observeSelectedDictionaries(): Flow<List<SelectedDictionaryEntity>> // наблюдение за текущим набором выбранных словарей

    @Query(
        """
        SELECT dictionaryId FROM selected_dictionaries
        ORDER BY dictionaryId ASC
        """
    )
    suspend fun getSelectedDictionaryIds(): List<String> // получаем список id выбранных словарей

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun selectDictionary(selectedDictionary: SelectedDictionaryEntity) // помечаем словарь как выбранный

    @Query(
        """
        DELETE FROM selected_dictionaries
        WHERE dictionaryId = :dictionaryId
        """
    )
    suspend fun unselectDictionary(dictionaryId: String) // снимаем выбор с конкретного словаря

    @Query("DELETE FROM selected_dictionaries")
    suspend fun clearSelectedDictionaries() // очищаем весь набор выбранных словарей

    @Query(
        """
        SELECT EXISTS(
            SELECT 1
            FROM selected_dictionaries
            WHERE dictionaryId = :dictionaryId
        )
        """
    )
    suspend fun isDictionarySelected(dictionaryId: String): Boolean // проверка выбран словарь или нет
}