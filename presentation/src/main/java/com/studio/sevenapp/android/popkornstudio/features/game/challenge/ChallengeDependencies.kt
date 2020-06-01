package com.studio.sevenapp.android.popkornstudio.features.game.challenge

import androidx.fragment.app.FragmentActivity
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module

fun Module.insertChallengeDependencies() {

    // ===========================
    // Activity Challenge
    viewModel {
        ChallengeViewModel(get())
    }

    factory { (fm: FragmentActivity) ->
        ChallengeQuestionAdapter(
            fm,
            emptyList()
        )
    }

    // ===========================
    // Fragment Question
    viewModel {
        ChallengeQuestionViewModel()
    }

    factory { (listening: ChallengeAnswerOptionsAdapter.ChallengeAnswerOptionItemClickListener) ->
        ChallengeAnswerOptionsAdapter(
            emptyList(),
            listening
        )
    }
}