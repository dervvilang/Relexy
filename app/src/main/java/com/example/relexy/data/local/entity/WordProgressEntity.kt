package com.example.relexy.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "word_progress",
    primaryKeys = ["wordId"],
    foreignKeys = [
        ForeignKey(
            entity = WordEntity::class,
            parentColumns = ["id"],
            childColumns = ["wordId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["dictionaryId"]),
        Index(value = ["status"]),
        Index(value = ["nextDueAt"])
    ]
)
data class WordProgressEntity(
    val wordId: String,
    val dictionaryId: String,

    // NEW, LEARNING, MASTERED
    val status: String,

    // VERY_HARD, HARD, NORMAL, EASY, VERY_EASY
    val difficulty: String,

    val nextDueAt: Long?,
    val currentIntervalDays: Double?,

    val firstSeenAt: Long?,
    val lastSeenAt: Long?,

    val successStreak: Int,
    val failStreak: Int,
    val successCount: Int
)