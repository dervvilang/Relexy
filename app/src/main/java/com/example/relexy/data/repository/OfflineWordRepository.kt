package com.example.relexy.data.repository

import androidx.room.withTransaction
import com.example.relexy.data.local.db.RelexyDatabase
import com.example.relexy.data.local.entity.WordDifficulty
import com.example.relexy.data.local.entity.WordEntity
import com.example.relexy.data.local.entity.WordExampleEntity
import com.example.relexy.data.local.entity.WordProgressEntity
import com.example.relexy.data.local.entity.WordStatus
import com.example.relexy.domain.model.WordDetails
import com.example.relexy.domain.model.WordExampleData
import com.example.relexy.domain.model.WordListItem
import com.example.relexy.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID

class OfflineWordRepository(
    private val db: RelexyDatabase
) : WordRepository {

    private val wordDao = db.wordDao()
    private val wordExampleDao = db.wordExampleDao()
    private val wordProgressDao = db.wordProgressDao()

    override fun observeWordsInDictionary(dictionaryId: String): Flow<List<WordListItem>> {
        return wordDao.observeWordsByDictionaryId(dictionaryId).map { words ->
            words.map { word ->
                val progress = wordProgressDao.getProgressByWordId(word.id)
                val status = progress?.status?.toWordStatus() ?: WordStatus.NEW

                word.toWordListItem(status = status)
            }
        }
    }

    override fun searchWordsInDictionary(
        dictionaryId: String,
        query: String
    ): Flow<List<WordListItem>> {
        return wordDao.searchWordsInDictionary(dictionaryId, query).map { words ->
            words.map { word ->
                val progress = wordProgressDao.getProgressByWordId(word.id)
                val status = progress?.status?.toWordStatus() ?: WordStatus.NEW

                word.toWordListItem(status = status)
            }
        }
    }

    override suspend fun getWordDetails(wordId: String): WordDetails? {
        val word = wordDao.getWordById(wordId) ?: return null

        val examples = wordExampleDao
            .getExamplesByWordId(wordId)
            .map { it.toWordExampleData() }

        val progress = wordProgressDao.getProgressByWordId(wordId)

        val status = progress?.status?.toWordStatus() ?: WordStatus.NEW
        val difficulty = progress?.difficulty?.toWordDifficulty() ?: WordDifficulty.NORMAL

        return word.toWordDetails(
            status = status,
            difficulty = difficulty,
            examples = examples
        )
    }

    override suspend fun createWord(
        dictionaryId: String,
        originalText: String,
        translationText: String,
        transcription: String?,
        imageLocalUri: String?,
        imageRemoteUrl: String?,
        examples: List<WordExampleData>
    ): String {
        val wordId = UUID.randomUUID().toString()
        val now = System.currentTimeMillis()

        val wordEntity = WordEntity(
            id = wordId,
            dictionaryId = dictionaryId,
            sourceRemoteWordId = null,
            originalText = originalText,
            translationText = translationText,
            transcription = transcription,
            imageLocalUri = imageLocalUri,
            imageRemoteUrl = imageRemoteUrl,
            createdAt = now,
            updatedAt = now,
            isDeleted = false
        )

        val progressEntity = WordProgressEntity(
            wordId = wordId,
            dictionaryId = dictionaryId,
            status = WordStatus.NEW.toStorageValue(),
            difficulty = WordDifficulty.NORMAL.toStorageValue(),
            nextDueAt = null,
            currentIntervalDays = null,
            firstSeenAt = null,
            lastSeenAt = null,
            successStreak = 0,
            failStreak = 0,
            successCount = 0
        )

        val exampleEntities = examples.mapIndexed { index, example ->
            WordExampleEntity(
                id = UUID.randomUUID().toString(),
                wordId = wordId,
                originalText = example.originalText,
                translationText = example.translationText,
                position = index,
                createdAt = now,
                updatedAt = now
            )
        }

        db.withTransaction {
            wordDao.insertWord(wordEntity)
            wordProgressDao.insertProgress(progressEntity)
            wordExampleDao.insertExamples(exampleEntities)
        }

        return wordId
    }

    override suspend fun updateWord(
        wordId: String,
        originalText: String,
        translationText: String,
        transcription: String?,
        imageLocalUri: String?,
        imageRemoteUrl: String?,
        examples: List<WordExampleData>
    ) {
        val currentWord = requireNotNull(wordDao.getWordById(wordId)) {
            "Word not found: $wordId"
        }

        val now = System.currentTimeMillis()

        val updatedWord = currentWord.copy(
            originalText = originalText,
            translationText = translationText,
            transcription = transcription,
            imageLocalUri = imageLocalUri,
            imageRemoteUrl = imageRemoteUrl,
            updatedAt = now
        )

        val newExamples = examples.mapIndexed { index, example ->
            WordExampleEntity(
                id = UUID.randomUUID().toString(),
                wordId = wordId,
                originalText = example.originalText,
                translationText = example.translationText,
                position = index,
                createdAt = now,
                updatedAt = now
            )
        }

        db.withTransaction {
            wordDao.updateWord(updatedWord)
            wordExampleDao.deleteExamplesByWordId(wordId)
            wordExampleDao.insertExamples(newExamples)
        }
    }

    override suspend fun deleteWord(wordId: String) {
        db.withTransaction {
            wordDao.hardDeleteWordById(wordId)
        }
    }

    override suspend fun updateWordDifficulty(
        wordId: String,
        difficulty: WordDifficulty
    ) {
        db.withTransaction {
            val word = getWordOrThrow(wordId)
            val progress = getOrCreateProgress(word)

            wordProgressDao.updateProgress(
                progress.copy(
                    difficulty = difficulty.toStorageValue()
                )
            )
        }
    }

    override suspend fun resetWordProgress(wordId: String) {
        db.withTransaction {
            val word = getWordOrThrow(wordId)
            val progress = getOrCreateProgress(word)

            wordProgressDao.updateProgress(
                progress.copy(
                    status = WordStatus.NEW.toStorageValue(),
                    difficulty = WordDifficulty.NORMAL.toStorageValue(),
                    nextDueAt = null,
                    currentIntervalDays = null,
                    firstSeenAt = null,
                    lastSeenAt = null,
                    successStreak = 0,
                    failStreak = 0,
                    successCount = 0
                )
            )
        }
    }

    override suspend fun markWordAsMastered(wordId: String) {
        db.withTransaction {
            val word = getWordOrThrow(wordId)
            val progress = getOrCreateProgress(word)

            wordProgressDao.updateProgress(
                progress.copy(
                    status = WordStatus.MASTERED.toStorageValue(),
                    nextDueAt = null
                )
            )
        }
    }

    override suspend fun startWordLearning(
        wordId: String,
        startedAtMillis: Long
    ) {
        db.withTransaction {
            val word = getWordOrThrow(wordId)
            val progress = getOrCreateProgress(word)

            wordProgressDao.updateProgress(
                progress.copy(
                    status = WordStatus.LEARNING.toStorageValue(),
                    difficulty = WordDifficulty.NORMAL.toStorageValue(),
                    nextDueAt = startedAtMillis,
                    currentIntervalDays = null,
                    firstSeenAt = progress.firstSeenAt ?: startedAtMillis,
                    lastSeenAt = startedAtMillis,
                    successStreak = 0,
                    failStreak = 0,
                    successCount = 0
                )
            )
        }
    }

    override suspend fun copyWordToDictionary(
        wordId: String,
        targetDictionaryId: String
    ): String {
        val word = requireNotNull(wordDao.getWordById(wordId)) {
            "Word not found: $wordId"
        }

        val examples = wordExampleDao
            .getExamplesByWordId(wordId)
            .map { it.toWordExampleData() }

        return createWord(
            dictionaryId = targetDictionaryId,
            originalText = word.originalText,
            translationText = word.translationText,
            transcription = word.transcription,
            imageLocalUri = word.imageLocalUri,
            imageRemoteUrl = word.imageRemoteUrl,
            examples = examples
        )
    }

    private suspend fun getWordOrThrow(wordId: String): WordEntity {
        return requireNotNull(wordDao.getWordById(wordId)) {
            "Word not found: $wordId"
        }
    }

    private suspend fun getOrCreateProgress(word: WordEntity): WordProgressEntity {
        val existing = wordProgressDao.getProgressByWordId(word.id)
        if (existing != null) return existing

        val newProgress = WordProgressEntity(
            wordId = word.id,
            dictionaryId = word.dictionaryId,
            status = WordStatus.NEW.toStorageValue(),
            difficulty = WordDifficulty.NORMAL.toStorageValue(),
            nextDueAt = null,
            currentIntervalDays = null,
            firstSeenAt = null,
            lastSeenAt = null,
            successStreak = 0,
            failStreak = 0,
            successCount = 0
        )

        wordProgressDao.insertProgress(newProgress)
        return newProgress
    }
}