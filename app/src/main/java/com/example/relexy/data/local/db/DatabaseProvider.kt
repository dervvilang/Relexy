package com.example.relexy.data.local.db

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile // нужен чтобы разные потоки видели актуальное значение
    private var INSTANCE: RelexyDatabase? = null
    // хранит единственный экземпляр базы в памяти

    fun getDatabase(context: Context): RelexyDatabase {
        return INSTANCE ?: synchronized(this) { // synchronized - защита от ситуации когда два потока одновременно захотят создать базу
            val instance = Room.databaseBuilder(
                context.applicationContext,
                RelexyDatabase::class.java,
                "relexy_database"
            )
                // TODO - раскомментировать в конце
                //.fallbackToDestructiveMigration(false)
                .fallbackToDestructiveMigration(true)
                .build()

            INSTANCE = instance
            instance
        }
    }
}