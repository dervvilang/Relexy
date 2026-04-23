package com.example.relexy.data.local.entity

enum class DictionaryOwnerType {
    OWNED,
    ADDED
}

enum class WordStatus {
    NEW,
    LEARNING,
    MASTERED
}

enum class WordDifficulty {
    VERY_HARD,
    HARD,
    NORMAL,
    EASY,
    VERY_EASY
}

enum class StudyActivityType {
    NEW_STARTED,
    NEW_MASTERED,
    REVIEW_SUCCESS,
    REVIEW_FAIL,
    MANUALLY_MASTERED,
    PROGRESS_RESET
}