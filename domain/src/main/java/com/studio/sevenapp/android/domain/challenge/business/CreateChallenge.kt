package com.studio.sevenapp.android.domain.challenge.business

import com.studio.sevenapp.android.domain.model.Answer
import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.Question
import java.util.*

class CreateChallenge {

    private var questionList: List<Question> = emptyList()

    fun create(
        genreName: String,
        questionList: List<Question>
    ): Challenge {
        this.questionList = questionList
        return Challenge(
            id = UUID.randomUUID().toString(),
            genre = genreName,
            questionList = selectQuestions()
        )
    }

    private fun selectQuestions(): List<Question> {
        val questionSelectedList: MutableList<Question> = mutableListOf()
        val totalQuestions = (questionList.size / 2)

        while (questionSelectedList.size < totalQuestions) {
            val question = questionList.random()
            if (!questionSelectedList.contains(question)) {
                val selectedAnswerList =
                    selectAnswers(question.answerList, getCorrectAnswer(question.answerList))
                question.answerList = emptyList()
                question.answerList = selectedAnswerList
                questionSelectedList.add(question)
            }
        }

        return questionSelectedList
    }

    private fun selectAnswers(
        answerList: List<Answer>,
        answerSelectedList: MutableList<Answer>
    ): List<Answer> {
        while (answerSelectedList.size < 3) {
            val answer = answerList.random()
            if (!answerSelectedList.contains(answer)) {
                answerSelectedList.add(answer)
            }
        }

        answerSelectedList.shuffle()

        return answerSelectedList
    }

    private fun getCorrectAnswer(answerList: List<Answer>): MutableList<Answer> {
        val answerCorrectList: List<Answer> = answerList.filter { answer -> answer.isCorrect }
        return answerCorrectList.toMutableList()
    }

}