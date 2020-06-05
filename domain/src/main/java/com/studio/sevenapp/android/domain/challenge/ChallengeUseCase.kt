package com.studio.sevenapp.android.domain.challenge

import com.studio.sevenapp.android.domain.challenge.business.QuestionStateEnum
import com.studio.sevenapp.android.domain.model.Answer
import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.MovieGenre
import com.studio.sevenapp.android.domain.model.Question

interface ChallengeUseCase {
    suspend fun saveAnswer(answer: Answer)
    suspend fun getQuestionsByState(state: QuestionStateEnum): List<Question>
    suspend fun getChallenged(genre: MovieGenre): Challenge
}