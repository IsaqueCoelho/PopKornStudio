package com.studio.sevenapp.android.popkornstudio.features.game.category

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module

fun Module.insertGameCategoryDependencies() {

    viewModel {
        GameCategoryViewModel(get())
    }

    factory { (listening: GameCategoryAdapter.GameCategoryItemClickListener) ->
        GameCategoryAdapter(
            emptyList(),
            listening
        )
    }
}