package com.studio.sevenapp.android.popkornstudio.features.game.challenge

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.studio.sevenapp.android.domain.challenge.ChallengeUseCase
import com.studio.sevenapp.android.domain.challenge.business.QuestionStateEnum
import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.Genre
import com.studio.sevenapp.android.domain.model.Question
import com.studio.sevenapp.android.popkornstudio.R
import com.studio.sevenapp.android.popkornstudio.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ChallengeViewModel(
    private val challengeUseCase: ChallengeUseCase
) : BaseViewModel() {

    private val scope = CoroutineScope(SupervisorJob())
    private val handler = CoroutineExceptionHandler { _, _ ->
        challengeLv.postValue(null)
        mustShowToastLv.postValue(Pair(first = true, second = R.string.failure_load_challenge))
    }

    private val challengeLv = MutableLiveData<Challenge?>()
    fun getChallenge(): LiveData<Challenge?> = challengeLv

    private val fragmentQuestionListLv = MutableLiveData<List<ChallengeQuestionFragment>>()
    fun showQuestionsFragments(): LiveData<List<ChallengeQuestionFragment>> = fragmentQuestionListLv

    @CallSuper
    override fun onViewResumed() {
        super.onViewResumed()
        loadStateLv.postValue(true)
    }

    fun getChallenge(genre: Genre) {
        scope.launch(handler) {
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

    fun cancelChallenge(challenge: Challenge) {
        scope.launch {
            challengeUseCase.saveChallenge(
                challenge = challenge,
                state = QuestionStateEnum.AVAILABLE
            )
        }
    }
}