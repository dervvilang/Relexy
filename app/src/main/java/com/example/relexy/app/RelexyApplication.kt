package com.example.relexy.app

import android.app.Application
import com.example.relexy.app.di.AppContainer
import com.example.relexy.app.di.DefaultAppContainer

class RelexyApplication : Application() {

    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()

        // создаём один контейнер зависимостей на всё приложение
        appContainer = DefaultAppContainer(this)
    }
}