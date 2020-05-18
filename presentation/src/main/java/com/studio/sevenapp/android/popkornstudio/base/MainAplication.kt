package com.studio.sevenapp.android.popkornstudio.base

import android.app.Application
import com.studio.sevenapp.android.data.di.dataModule
import com.studio.sevenapp.android.domain.di.domainModule
import com.studio.sevenapp.android.firebase.di.firebaseModule
import com.studio.sevenapp.android.popkornstudio.di.presentationModule
import org.koin.core.context.startKoin

class MainAplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(
                presentationModule,
                domainModule,
                dataModule,
                firebaseModule
            ))
        }
    }
}