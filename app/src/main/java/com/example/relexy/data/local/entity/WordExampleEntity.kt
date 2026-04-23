package com.example.relexy.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "word_examples",
    foreignKeys = [
        ForeignKey(
            entity = WordEntity::class,
            parentColumns = ["id"],
            childColumns = ["wordId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["wordId"])
    ]
)
data class WordExampleEntity(
    @PrimaryKey
    val id: String,

    val wordId: String,

    val originalText: String,
    val translationText: String,

    val position: Int,

    val createdAt: Long,
    val updatedAt: Long
)