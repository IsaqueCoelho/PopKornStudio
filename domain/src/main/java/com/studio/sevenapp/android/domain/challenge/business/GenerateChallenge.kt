package com.studio.sevenapp.android.domain.challenge.business

import com.studio.sevenapp.android.domain.model.Answer
import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.Genre
import com.studio.sevenapp.android.domain.model.Question
import java.util.*

class GenerateChallenge {

    private var questionList: List<Question> = emptyList()

    fun create(
        genre: Genre,
        level: Int,
        questionList: List<Question>
    ): Challenge {
        this.questionList = questionList
        return Challenge(
            id = UUID.randomUUID().toString(),
            genre = genre,
            level = level,
            division = ChallengeDivisionEnum.ALPHA,
            questionList = questionList
        )
    }

    fun reOrganize(challenge: Challenge): Challenge {
        this.questionList = challenge.questionList

        val totalList = if (questionList.size > 10) questionList.size / 2 else questionList.size

        return Challenge(
            id = challenge.id,
            genre = challenge.genre,
            level = challenge.level,
            division = challenge.division,
            questionList = selectQuestions(totalList)
        )
    }

    private fun selectQuestions(totalQuestions: Int): List<Question> {
        val questionSelectedList: MutableList<Question> = mutableListOf()

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