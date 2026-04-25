package com.example.relexy.domain.model

import com.example.relexy.data.local.entity.DictionaryOwnerType

data class DictionaryDetails(
    val id: String,
    val title: String,
    val description: String?,
    val iconKey: String,
    val ownerType: DictionaryOwnerType,
    val isPublished: Boolean,
    val wordCount: Int
)