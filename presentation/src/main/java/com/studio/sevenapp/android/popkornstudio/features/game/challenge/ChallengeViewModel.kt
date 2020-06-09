package com.studio.sevenapp.android.popkornstudio.features.game.challenge

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.studio.sevenapp.android.domain.challenge.ChallengeUseCase
import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.Genre
import com.studio.sevenapp.android.domain.model.Question
import com.studio.sevenapp.android.popkornstudio.base.BaseViewModel
import kotlinx.coroutines.launch

class ChallengeViewModel(
    private val challengeUseCase: ChallengeUseCase
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

    fun getChallenge(genre: Genre) {
        viewModelScope.launch {
            challengeLv.postValue(challengeUseCase.getChallenged(genre))
        }
    }

    fun createChallengeQuestionFragments(questionList: List<Question>) {
        val challengeFragmentList = mutableListOf<ChallengeQuestionFragment>()

        questionList.forEachIndexed { index, question ->
            challengeFragmentList.add(
                ChallengeQuestionFragment.newInstance(question, index + 1)
            )
        }

        fragmentQuestionListLv.postValue(challengeFragmentList)
    }
}