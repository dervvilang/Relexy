package com.example.relexy.data.repository

import androidx.room.withTransaction
import com.example.relexy.data.local.db.RelexyDatabase
import com.example.relexy.data.local.entity.DictionaryEntity
import com.example.relexy.data.local.entity.DictionaryOwnerType
import com.example.relexy.data.local.entity.SelectedDictionaryEntity
import com.example.relexy.data.local.entity.WordStatus
import com.example.relexy.domain.model.DictionaryDetails
import com.example.relexy.domain.model.DictionaryListItem
import com.example.relexy.domain.repository.DictionaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import kotlin.math.roundToInt

class OfflineDictionaryRepository(
    private val db: RelexyDatabase
) : DictionaryRepository {

    private val dictionaryDao = db.dictionaryDao()
    private val wordDao = db.wordDao()
    private val wordProgressDao = db.wordProgressDao()
    private val selectedDictionaryDao = db.selectedDictionaryDao()

    override fun observeAllDictionaries(): Flow<List<DictionaryListItem>> {
        return dictionaryDao.observeAllDictionaries().map { dictionaries ->
            dictionaries.map { dictionary ->
                dictionary.toDictionaryListItem(
                    wordCount = wordDao.countWordsByDictionaryId(dictionary.id),
                    masteredPercent = calculateMasteredPercent(dictionary.id)
                )
            }
        }
    }

    override fun observeOwnDictionaries(): Flow<List<DictionaryListItem>> {
        return dictionaryDao
            .observeDictionariesByOwnerType(DictionaryOwnerType.OWNED.toStorageValue())
            .map { dictionaries ->
                dictionaries.map { dictionary ->
                    dictionary.toDictionaryListItem(
                        wordCount = wordDao.countWordsByDictionaryId(dictionary.id),
                        masteredPercent = calculateMasteredPercent(dictionary.id)
                    )
                }
            }
    }

    override fun observeAddedDictionaries(): Flow<List<DictionaryListItem>> {
        return dictionaryDao
            .observeDictionariesByOwnerType(DictionaryOwnerType.ADDED.toStorageValue())
            .map { dictionaries ->
                dictionaries.map { dictionary ->
                    dictionary.toDictionaryListItem(
                        wordCount = wordDao.countWordsByDictionaryId(dictionary.id),
                        masteredPercent = calculateMasteredPercent(dictionary.id)
                    )
                }
            }
    }

    override suspend fun getDictionaryDetails(dictionaryId: String): DictionaryDetails? {
        val dictionary = dictionaryDao.getDictionaryById(dictionaryId) ?: return null
        val wordCount = wordDao.countWordsByDictionaryId(dictionaryId)

        return dictionary.toDictionaryDetails(wordCount = wordCount)
    }

    override suspend fun createDictionary(
        title: String,
        description: String?,
        iconKey: String
    ): String {
        val dictionaryId = UUID.randomUUID().toString()
        val now = System.currentTimeMillis()

        val entity = DictionaryEntity(
            id = dictionaryId,
            title = title,
            description = description,
            iconKey = iconKey,
            ownerType = DictionaryOwnerType.OWNED.toStorageValue(),
            isPublished = false,
            remotePublicationId = null,
            sourcePublicationId = null,
            sourceAuthorId = null,
            sourceAuthorNickname = null,
            createdAt = now,
            updatedAt = now,
            isDeleted = false
        )

        dictionaryDao.insertDictionary(entity)
        return dictionaryId
    }

    override suspend fun updateDictionary(
        dictionaryId: String,
        title: String,
        description: String?,
        iconKey: String
    ) {
        val current = requireNotNull(dictionaryDao.getDictionaryById(dictionaryId)) {
            "Dictionary not found: $dictionaryId"
        }

        val updated = current.copy(
            title = title,
            description = description,
            iconKey = iconKey,
            updatedAt = System.currentTimeMillis()
        )

        dictionaryDao.updateDictionary(updated)
    }

    override suspend fun deleteDictionary(dictionaryId: String) {
        db.withTransaction {
            dictionaryDao.hardDeleteDictionaryById(dictionaryId)
        }
    }

    override suspend fun clearDictionary(dictionaryId: String) {
        db.withTransaction {
            wordDao.hardDeleteWordsByDictionaryId(dictionaryId)
        }
    }

    override suspend fun resetDictionaryProgress(dictionaryId: String) {
        wordProgressDao.resetProgressByDictionaryId(dictionaryId)
    }

    override suspend fun selectDictionary(dictionaryId: String) {
        selectedDictionaryDao.selectDictionary(
            SelectedDictionaryEntity(dictionaryId = dictionaryId)
        )
    }

    override suspend fun unselectDictionary(dictionaryId: String) {
        selectedDictionaryDao.unselectDictionary(dictionaryId)
    }

    override suspend fun setDictionarySelected(
        dictionaryId: String,
        selected: Boolean
    ) {
        if (selected) {
            selectDictionary(dictionaryId)
        } else {
            unselectDictionary(dictionaryId)
        }
    }

    override fun observeSelectedDictionaryIds(): Flow<Set<String>> {
        return selectedDictionaryDao.observeSelectedDictionaries().map { selectedItems ->
            selectedItems.map { it.dictionaryId }.toSet()
        }
    }

    override suspend fun getSelectedDictionaryIds(): List<String> {
        return selectedDictionaryDao.getSelectedDictionaryIds()
    }

    override suspend fun clearSelectedDictionaries() {
        selectedDictionaryDao.clearSelectedDictionaries()
    }

    private suspend fun calculateMasteredPercent(dictionaryId: String): Int {
        val totalWords = wordDao.countWordsByDictionaryId(dictionaryId)
        if (totalWords == 0) return 0

        val masteredWords = wordProgressDao.countWordsByStatus(
            dictionaryId = dictionaryId,
            status = WordStatus.MASTERED.toStorageValue()
        )

        return ((masteredWords.toDouble() / totalWords) * 100).roundToInt()
    }
}