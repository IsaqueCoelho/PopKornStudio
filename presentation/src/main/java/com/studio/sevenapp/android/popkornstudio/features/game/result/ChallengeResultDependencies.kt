package com.studio.sevenapp.android.popkornstudio.features.game.result

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module

fun Module.insertChallengeResult(){
    viewModel {
        ChallengeResultViewModel(get())
    }
}