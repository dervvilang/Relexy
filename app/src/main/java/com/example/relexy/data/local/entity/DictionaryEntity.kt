package com.example.relexy.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dictionaries")
data class DictionaryEntity(
    @PrimaryKey
    val id: String,

    val title: String,
    val description: String?,
    val iconKey: String,

    val ownerType: String,
    val isPublished: Boolean,
    val remotePublicationId: String?,
    val sourcePublicationId: String?,
    val sourceAuthorId: String?,
    val sourceAuthorNickname: String?,

    val createdAt: Long,
    val updatedAt: Long,

    val isDeleted: Boolean
)