package com.studio.sevenapp.android.domain.challenge

import com.studio.sevenapp.android.domain.challenge.business.QuestionStateEnum
import com.studio.sevenapp.android.domain.model.Movie
import com.studio.sevenapp.android.domain.model.Question

interface ChallengeRepository {
    suspend fun insertQuestions(questionList: List<Question>)
    suspend fun getMoviesByGenre(page: Int = 1, genre: Int): List<Movie>
    suspend fun updateQuestion(question: Question)
    suspend fun getQuestionsByState(state: QuestionStateEnum): List<Question>
}