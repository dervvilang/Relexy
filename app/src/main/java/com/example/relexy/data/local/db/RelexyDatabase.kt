package com.example.relexy.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.relexy.data.local.dao.DictionaryDao
import com.example.relexy.data.local.dao.SelectedDictionaryDao
import com.example.relexy.data.local.dao.StudyActivityDao
import com.example.relexy.data.local.dao.WordDao
import com.example.relexy.data.local.dao.WordExampleDao
import com.example.relexy.data.local.dao.WordProgressDao
import com.example.relexy.data.local.entity.DictionaryEntity
import com.example.relexy.data.local.entity.SelectedDictionaryEntity
import com.example.relexy.data.local.entity.StudyActivityEntity
import com.example.relexy.data.local.entity.WordEntity
import com.example.relexy.data.local.entity.WordExampleEntity
import com.example.relexy.data.local.entity.WordProgressEntity

@Database(
    entities = [
        DictionaryEntity::class,
        WordEntity::class,
        WordExampleEntity::class,
        WordProgressEntity::class,
        SelectedDictionaryEntity::class,
        StudyActivityEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class RelexyDatabase : RoomDatabase() {

    abstract fun dictionaryDao(): DictionaryDao

    abstract fun wordDao(): WordDao

    abstract fun wordExampleDao(): WordExampleDao

    abstract fun wordProgressDao(): WordProgressDao

    abstract fun selectedDictionaryDao(): SelectedDictionaryDao

    abstract fun studyActivityDao(): StudyActivityDao
}