package com.studio.sevenapp.android.popkornstudio.features.game.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.studio.sevenapp.android.domain.challenge.ChallengeUseCase
import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.popkornstudio.base.BaseViewModel
import kotlinx.coroutines.launch

class ChallengeResultViewModel(
    private val challengeUseCase: ChallengeUseCase
) : BaseViewModel() {

    private val challengeLv = MutableLiveData<Challenge>()
    fun showChallenge(): LiveData<Challenge> = challengeLv

    fun getChallengeById(challengeId: String){
        viewModelScope.launch {
            val challenge = challengeUseCase.getChallengeById(challengeId = challengeId)
            challengeLv.postValue(challenge)
        }
    }
}