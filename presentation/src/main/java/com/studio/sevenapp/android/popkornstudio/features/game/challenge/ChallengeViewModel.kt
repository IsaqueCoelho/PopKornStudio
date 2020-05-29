package com.studio.sevenapp.android.popkornstudio.features.game.challenge

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.studio.sevenapp.android.domain.challenge.ChallengeUseCase
import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.ChallengeQuestion
import com.studio.sevenapp.android.domain.model.ChallengeQuestionAnswerOption
import com.studio.sevenapp.android.domain.model.MovieGenre
import com.studio.sevenapp.android.popkornstudio.base.BaseViewModel
import kotlinx.coroutines.launch

class ChallengeViewModel(
    private val challenge: ChallengeUseCase
) : BaseViewModel() {

    var challengeType: MovieGenre? = null

    private val challengeLv = MutableLiveData<Challenge>()
    private val fragmentQuestionListLv = MutableLiveData<List<ChallengeQuestionFragment>>()

    @CallSuper
    override fun onViewResumed() {
        super.onViewResumed()
        loadStateLv.postValue(true)
        viewModelScope.launch {
            getChallengeList()
        }
    }

    fun showQuestionsFragments(): LiveData<List<ChallengeQuestionFragment>> = fragmentQuestionListLv
    fun getChallenge(): LiveData<Challenge> = challengeLv

    private suspend fun getChallengeList() {
        challengeLv.postValue(challenge.getChallenge(challengeType!!.id))
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

    private fun getMockChallegeQuestionList() = mutableListOf(
        ChallengeQuestion(
            123,
            "1. Qual o Filme da sinopse a seguir?",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book",
            mutableListOf(
                ChallengeQuestionAnswerOption(
                    "1234",
                    "Filme A",
                    false
                ),
                ChallengeQuestionAnswerOption(
                    "12345",
                    "Filme B",
                    true
                ),
                ChallengeQuestionAnswerOption(
                    "123456",
                    "Filme C",
                    false
                )
            )
        ),
        ChallengeQuestion(
            1234,
            "2. Os Seguintes personagens são de qual filme?",
            "Tony Stark(Robert Downey Jr) \n Peter Parker (Tom Holland) \n Tor (Chris Hemsworth)",
            mutableListOf(
                ChallengeQuestionAnswerOption(
                    "1234",
                    "Vingadores: Guerra Infinita",
                    true
                ),
                ChallengeQuestionAnswerOption(
                    "12345",
                    "Monstros SA",
                    false
                ),
                ChallengeQuestionAnswerOption(
                    "123456",
                    "Lala Land",
                    false
                )
            )
        ),
        ChallengeQuestion(
            12345,
            "3. Qual a data de lançamento do Filme a seguir?",
            "Ad Astra",
            mutableListOf(
                ChallengeQuestionAnswerOption(
                    "1234",
                    "17/09/2019",
                    false
                ),
                ChallengeQuestionAnswerOption(
                    "12345",
                    "24/04/2019",
                    false
                ),
                ChallengeQuestionAnswerOption(
                    "123456",
                    "25/12/2020",
                    true
                )
            )
        )
    )
}