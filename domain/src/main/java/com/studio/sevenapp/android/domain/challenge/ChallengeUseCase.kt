package com.studio.sevenapp.android.domain.challenge

import com.studio.sevenapp.android.domain.model.Answer
import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.MovieGenre

interface ChallengeUseCase {
    suspend fun getChallenge(genre: MovieGenre) : Challenge
    suspend fun getChallengeById(challengeId: String) : Challenge
    suspend fun saveAnswer(answer: Answer)
}