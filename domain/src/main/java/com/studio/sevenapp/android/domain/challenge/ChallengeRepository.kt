package com.studio.sevenapp.android.domain.challenge

import com.studio.sevenapp.android.domain.challenge.business.QuestionStateEnum
import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.Movie
import com.studio.sevenapp.android.domain.model.Question

interface ChallengeRepository {
    suspend fun getMoviesByGenre(page: Int = 1, genre: Int): List<Movie>
    suspend fun getChallengeByGenre(genre: String, questionStateEnum: QuestionStateEnum): Challenge?
    suspend fun getQuestionsByState(state: QuestionStateEnum): List<Question>
    suspend fun insertChallenge(challenge: Challenge)
    suspend fun updateQuestion(question: Question)
    suspend fun updateChallenge(challenge: Challenge)
    suspend fun deleteData(challenge: Challenge)
}