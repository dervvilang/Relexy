package com.example.relexy.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.relexy.data.local.entity.StudyActivityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StudyActivityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivity(activity: StudyActivityEntity) // сохранение одного событие активности

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivities(activities: List<StudyActivityEntity>) // массовая вставка

    @Query(
        """
        SELECT * FROM study_activity
        WHERE occurredAt BETWEEN :startTime AND :endTime
        ORDER BY occurredAt DESC
        """
    )
    fun observeActivitiesInRange(
        startTime: Long,
        endTime: Long
    ): Flow<List<StudyActivityEntity>>
    // наблюдаем активность за период

    @Query(
        """
        SELECT COUNT(*) FROM study_activity
        WHERE type = :type
        AND occurredAt BETWEEN :startTime AND :endTime
        """
    )
    suspend fun countActivitiesByType(
        type: String,
        startTime: Long,
        endTime: Long
    ): Int
    // сколько событий конкретного типа было за период

    @Query(
        """
        SELECT COUNT(*) FROM study_activity
        WHERE occurredAt BETWEEN :startTime AND :endTime
        """
    )
    suspend fun countAllActivitiesInRange(
        startTime: Long,
        endTime: Long
    ): Int
    // общее количество любых учебных действий за период

    @Query(
        """
        SELECT DISTINCT date(occurredAt / 1000, 'unixepoch', 'localtime')
        FROM study_activity
        WHERE occurredAt BETWEEN :startTime AND :endTime
        ORDER BY 1 ASC
        """
    )
    suspend fun getActiveDaysInRange(
        startTime: Long,
        endTime: Long
    ): List<String>
    // получение списка уникальных активных дней
}