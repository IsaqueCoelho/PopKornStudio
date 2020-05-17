package com.studio.sevenapp.android.popkornstudio.splash

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module


fun Module.includeSplashScreen(){

    viewModel {
        SplashViewModel()
    }
}