package com.studio.sevenapp.android.popkornstudio.base

import com.studio.sevenapp.android.popkornstudio.splash.includeSplashScreen
import org.koin.dsl.module

val presentationModule = module {

    // Splash Screen
    includeSplashScreen()
}