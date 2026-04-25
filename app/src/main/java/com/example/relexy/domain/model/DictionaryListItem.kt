package com.example.relexy.domain.model

import com.example.relexy.data.local.entity.DictionaryOwnerType

data class DictionaryListItem(
    val id: String,
    val title: String,
    val iconKey: String,
    val ownerType: DictionaryOwnerType,
    val wordCount: Int,
    val masteredPercent: Int
)