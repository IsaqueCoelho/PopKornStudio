package com.studio.sevenapp.android.popkornstudio.base

import android.app.Application
import org.koin.core.context.startKoin

class MainAplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(
                presentationModule
            ))
        }
    }
}