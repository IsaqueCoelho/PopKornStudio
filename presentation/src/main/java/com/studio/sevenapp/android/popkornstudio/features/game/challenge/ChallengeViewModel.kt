package com.studio.sevenapp.android.popkornstudio.features.game.challenge

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.studio.sevenapp.android.domain.challenge.ChallengeUseCase
import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.ChallengeQuestion
import com.studio.sevenapp.android.domain.model.MovieGenre
import com.studio.sevenapp.android.popkornstudio.base.BaseViewModel
import kotlinx.coroutines.launch

class ChallengeViewModel(
    private val challenge: ChallengeUseCase
) : BaseViewModel() {

    private val challengeLv = MutableLiveData<Challenge>()
    private val fragmentQuestionListLv = MutableLiveData<List<ChallengeQuestionFragment>>()

    @CallSuper
    override fun onViewResumed() {
        super.onViewResumed()
        loadStateLv.postValue(true)
    }

    fun showQuestionsFragments(): LiveData<List<ChallengeQuestionFragment>> = fragmentQuestionListLv
    fun getChallenge(): LiveData<Challenge> = challengeLv

    fun getChallenge(movieGenre: MovieGenre) {
        viewModelScope.launch {
            challengeLv.postValue(challenge.getChallenge(movieGenre))
        }
    }

    fun createChallengeQuestionFragments(challengeQuestionList: List<ChallengeQuestion>) {
        val challengeFragmentList = mutableListOf<ChallengeQuestionFragment>()

        challengeQuestionList.forEach { question ->
            challengeFragmentList.add(
                ChallengeQuestionFragment.newInstance(question)
            )
        }

        fragmentQuestionListLv.postValue(challengeFragmentList)
    }
}