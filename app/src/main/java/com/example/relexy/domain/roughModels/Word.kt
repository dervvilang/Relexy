package com.example.relexy.domain.roughModels

data class Word(
    val id: String,
    val originalText: String,
    val translationText: String,
    val status: WordStatus,
    val transcription: String? = null,
    val imagePath: String? = null,
    val example: String? = null,
    val exampleTranslation: String? = null
)