package com.studio.sevenapp.android.popkornstudio.features.home

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module

fun Module.insertHomeDependencies(){
    viewModel {
        HomeViewModel(get())
    }
}