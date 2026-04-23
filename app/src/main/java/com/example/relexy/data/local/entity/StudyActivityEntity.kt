package com.example.relexy.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "study_activity",
    indices = [
        Index(value = ["occurredAt"]),
        Index(value = ["dictionaryId"]),
        Index(value = ["type"])
    ]
)
data class StudyActivityEntity(
    @PrimaryKey
    val id: String,

    val wordId: String?,
    val dictionaryId: String?,

    // NEW_STARTED, NEW_MASTERED, REVIEW_SUCCESS, REVIEW_FAIL и т.д.
    val type: String,

    val occurredAt: Long
)