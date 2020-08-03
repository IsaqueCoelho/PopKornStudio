package com.studio.sevenapp.android.popkornstudio.features.news

import com.studio.sevenapp.android.popkornstudio.base.EmptyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module

fun Module.insertNews(){
    viewModel {
        EmptyViewModel()
    }
}