package com.studio.sevenapp.android.domain.challenge

import com.studio.sevenapp.android.domain.model.Answer
import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.Movie

interface ChallengeRepository {
    suspend fun getMoviesByGenre(page: Int = 1, genre: Int) : List<Movie>
    suspend fun getChallengeById(challengeId: String) : Challenge
    suspend fun insertChallenge(challenge: Challenge)
    suspend fun updatedAnswer(answer: Answer)
}