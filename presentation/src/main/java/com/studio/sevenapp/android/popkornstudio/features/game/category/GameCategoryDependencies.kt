package com.studio.sevenapp.android.popkornstudio.features.game.category

import com.studio.sevenapp.android.domain.model.MovieGenre
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module

fun Module.insertGameCategoryDependencies() {

    viewModel {
        GameCategoryViewModel(get())
    }

    factory { (elements: List<MovieGenre>?, listening: GameCategoryAdapter.GameCategoryItemClickListener) ->
        GameCategoryAdapter(
            elements,
            listening
        )
    }
}