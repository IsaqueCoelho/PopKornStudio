package com.studio.sevenapp.android.popkornstudio.base

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.studio.sevenapp.android.data.di.dataModule
import com.studio.sevenapp.android.domain.di.domainModule
import com.studio.sevenapp.android.firebase.di.firebaseModule
import com.studio.sevenapp.android.popkornstudio.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainAplication : Application() {
    override fun onCreate() {
        super.onCreate()

        MobileAds.initialize(this) {}

        startKoin {
            androidContext(this@MainAplication)
            modules(listOf(
                presentationModule,
                domainModule,
                dataModule,
                firebaseModule
            ))
        }
    }
}