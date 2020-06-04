package com.studio.sevenapp.android.domain.challenge

import com.studio.sevenapp.android.domain.challenge.business.QuestionStateEnum
import com.studio.sevenapp.android.domain.model.*

interface ChallengeUseCase {
    suspend fun createQuestions(genre: MovieGenre)
    suspend fun saveAnswer(answer: Answer)
    suspend fun getQuestionsByState(state: QuestionStateEnum): List<Question>
    suspend fun getChallenged(): Challenge
}