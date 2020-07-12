package com.studio.sevenapp.android.popkornstudio.features.ranking

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module

fun Module.insertRanking() {

    viewModel {
        RankingViewModel(get())
    }

    single {
        RankingAdapter(emptyList())
    }

    factory { (listening: RankingCategoryAdapter.RankingCategoryItemClickListener) ->
        RankingCategoryAdapter(
            emptyList(),
            listening
        )
    }
}