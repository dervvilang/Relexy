package com.example.relexy.data.repository

import com.example.relexy.data.local.entity.DictionaryEntity
import com.example.relexy.data.local.entity.DictionaryOwnerType
import com.example.relexy.data.local.entity.WordDifficulty
import com.example.relexy.data.local.entity.WordEntity
import com.example.relexy.data.local.entity.WordExampleEntity
import com.example.relexy.data.local.entity.WordStatus
import com.example.relexy.domain.model.DictionaryDetails
import com.example.relexy.domain.model.DictionaryListItem
import com.example.relexy.domain.model.WordDetails
import com.example.relexy.domain.model.WordExampleData
import com.example.relexy.domain.model.WordListItem

// String <-> enum

fun String.toDictionaryOwnerType(): DictionaryOwnerType {
    return DictionaryOwnerType.valueOf(this)
}

fun String.toWordStatus(): WordStatus {
    return WordStatus.valueOf(this)
}

fun String.toWordDifficulty(): WordDifficulty {
    return WordDifficulty.valueOf(this)
}

fun DictionaryOwnerType.toStorageValue(): String = name

fun WordStatus.toStorageValue(): String = name

fun WordDifficulty.toStorageValue(): String = name


// Entity -> domain model

fun DictionaryEntity.toDictionaryListItem(
    wordCount: Int,
    masteredPercent: Int
): DictionaryListItem {
    return DictionaryListItem(
        id = id,
        title = title,
        iconKey = iconKey,
        ownerType = ownerType.toDictionaryOwnerType(),
        wordCount = wordCount,
        masteredPercent = masteredPercent
    )
}

fun DictionaryEntity.toDictionaryDetails(
    wordCount: Int
): DictionaryDetails {
    return DictionaryDetails(
        id = id,
        title = title,
        description = description,
        iconKey = iconKey,
        ownerType = ownerType.toDictionaryOwnerType(),
        isPublished = isPublished,
        wordCount = wordCount
    )
}

fun WordEntity.toWordListItem(
    status: WordStatus
): WordListItem {
    return WordListItem(
        id = id,
        originalText = originalText,
        translationText = translationText,
        status = status
    )
}

fun WordExampleEntity.toWordExampleData(): WordExampleData {
    return WordExampleData(
        originalText = originalText,
        translationText = translationText,
        position = position
    )
}

fun WordEntity.toWordDetails(
    status: WordStatus,
    difficulty: WordDifficulty,
    examples: List<WordExampleData>
): WordDetails {
    return WordDetails(
        id = id,
        dictionaryId = dictionaryId,
        originalText = originalText,
        translationText = translationText,
        transcription = transcription,
        imageLocalUri = imageLocalUri,
        imageRemoteUrl = imageRemoteUrl,
        status = status,
        difficulty = difficulty,
        examples = examples
    )
}