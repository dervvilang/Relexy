package com.example.relexy.app.di

import android.content.Context
import com.example.relexy.app.RelexyApplication
import com.example.relexy.data.local.db.DatabaseProvider
import com.example.relexy.data.local.db.RelexyDatabase
import com.example.relexy.data.repository.OfflineDictionaryRepository
import com.example.relexy.data.repository.OfflineWordRepository
import com.example.relexy.domain.repository.DictionaryRepository
import com.example.relexy.domain.repository.WordRepository

interface AppContainer {
    val dictionaryRepository: DictionaryRepository
    val wordRepository: WordRepository
}

class DefaultAppContainer(
    private val context: Context
) : AppContainer {

    // общая база данных на всё приложение
    // by lazy - база создастся только в момент первого обращения
    private val database: RelexyDatabase by lazy {
        DatabaseProvider.getDatabase(context)
    }

    // репозиторий словарей
    override val dictionaryRepository: DictionaryRepository by lazy {
        OfflineDictionaryRepository(database)
    }

    // репозиторий слов
    override val wordRepository: WordRepository by lazy {
        OfflineWordRepository(database)
    }
}

// из любого context можно получить appContainer
val Context.appContainer: AppContainer
    get() = (applicationContext as RelexyApplication).appContainer