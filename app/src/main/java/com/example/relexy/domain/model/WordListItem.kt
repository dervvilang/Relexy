package com.example.relexy.domain.model

import com.example.relexy.data.local.entity.WordStatus

data class WordListItem(
    val id: String,
    val originalText: String,
    val translationText: String,
    val status: WordStatus
)