package com.example.relexy.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "words",
    foreignKeys = [
        ForeignKey(
            entity = DictionaryEntity::class,
            parentColumns = ["id"],
            childColumns = ["dictionaryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["dictionaryId"])
    ]
)
data class WordEntity(
    @PrimaryKey
    val id: String,

    val dictionaryId: String,

    val sourceRemoteWordId: String?,

    val originalText: String,
    val translationText: String,
    val transcription: String?,

    val imageLocalUri: String?,
    val imageRemoteUrl: String?,

    val createdAt: Long,
    val updatedAt: Long,

    val isDeleted: Boolean
)