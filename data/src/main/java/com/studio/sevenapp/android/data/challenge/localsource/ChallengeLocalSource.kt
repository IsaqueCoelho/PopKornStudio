package com.studio.sevenapp.android.data.challenge.localsource

import com.studio.sevenapp.android.domain.model.Answer
import com.studio.sevenapp.android.domain.model.Question

interface ChallengeLocalSource {
    suspend fun insertQuestions(questionList: List<Question>)
    suspend fun updatedAnswer(answer: Answer)
    suspend fun getQuestionsByState(state: String): List<Question>
    suspend fun deleteData(questionList: List<Question>)
}