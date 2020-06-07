package com.studio.sevenapp.android.popkornstudio.features.game.challenge

import androidx.lifecycle.viewModelScope
import com.studio.sevenapp.android.domain.challenge.ChallengeUseCase
import com.studio.sevenapp.android.domain.model.Question
import com.studio.sevenapp.android.popkornstudio.base.BaseViewModel
import kotlinx.coroutines.launch

class ChallengeQuestionViewModel(
    private val challengeUseCase: ChallengeUseCase
) : BaseViewModel() {

    fun saveQuestion(question: Question){
        viewModelScope.launch {
            challengeUseCase.saveQuestion(question = question)
        }
    }
}