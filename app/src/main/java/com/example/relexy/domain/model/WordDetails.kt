package com.example.relexy.domain.model

import com.example.relexy.data.local.entity.WordDifficulty
import com.example.relexy.data.local.entity.WordStatus

data class WordDetails(
    val id: String,
    val dictionaryId: String,
    val originalText: String,
    val translationText: String,
    val transcription: String?,
    val imageLocalUri: String?,
    val imageRemoteUrl: String?,
    val status: WordStatus,
    val difficulty: WordDifficulty,
    val examples: List<WordExampleData>
)