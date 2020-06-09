package com.studio.sevenapp.android.popkornstudio.features.game.result

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.studio.sevenapp.android.domain.challenge.ChallengeUseCase
import com.studio.sevenapp.android.domain.model.ChallengeResult
import com.studio.sevenapp.android.popkornstudio.base.BaseViewModel
import kotlinx.coroutines.launch

class ChallengeResultViewModel(
    private val challengeUseCase: ChallengeUseCase
) : BaseViewModel() {

    private val resultLv = MutableLiveData<ChallengeResult>()
    fun showResult(): LiveData<ChallengeResult> = resultLv

    @CallSuper
    override fun onViewResumed() {
        super.onViewResumed()
        loadStateLv.postValue(true)
    }

    fun getChallengeResult(challengeGenre: String) {
        viewModelScope.launch {
            val challengeResult = challengeUseCase.getChallengeResult(genre = challengeGenre)
            resultLv.postValue(challengeResult)
        }
    }
}