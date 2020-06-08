package com.studio.sevenapp.android.data.challenge.localsource

import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.Question

interface ChallengeLocalSource {
    suspend fun getChallengeByGenre(genre: String): Challenge?
    suspend fun getQuestionsByState(state: String): List<Question>
    suspend fun insertChallenge(challenge: Challenge)
    suspend fun updateQuestion(question: Question)
    suspend fun deleteData(challenge: Challenge)
}