package com.studio.sevenapp.android.popkornstudio.features.game.challenge

import androidx.lifecycle.viewModelScope
import com.studio.sevenapp.android.domain.challenge.ChallengeUseCase
import com.studio.sevenapp.android.domain.model.Answer
import com.studio.sevenapp.android.popkornstudio.base.BaseViewModel
import kotlinx.coroutines.launch

class ChallengeQuestionViewModel(
    private val challengeUseCase: ChallengeUseCase
) : BaseViewModel() {

    fun saveAnswer(answer: Answer){
        viewModelScope.launch {
            challengeUseCase.saveAnswer(answer = answer)
        }
    }
}