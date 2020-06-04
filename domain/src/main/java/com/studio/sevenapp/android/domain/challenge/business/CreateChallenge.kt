package com.studio.sevenapp.android.domain.challenge.business

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
        val questionPositionList: List<Int> = getQuestionPositions()

        for (questionPosition in questionList.indices) {
            if (questionPositionList.contains(questionPosition)) {
                questionSelectedList.add(
                    questionList[questionPosition]
                )
            }
        }

        return questionSelectedList
    }

    private fun getQuestionPositions(): List<Int> {
        val questionPositionList = mutableListOf<Int>()

        while (questionPositionList.size < 10) {
            val position = (questionList.indices).random()

            if (!questionPositionList.contains(position)) {
                questionPositionList.add(position)
            }
        }

        return questionPositionList
    }


    /*private fun getAnswerOptions(moviePosition: Int): List<Answer> {
        val answerList: MutableList<Answer> = mutableListOf()
        val positionCorrectAnswer = (0..2).random()
        val fakeMoviePositions = getFakeMoviesPositions(moviePosition)
        var fakeMoviePositionIndex = 0

        for (answerPosition in 0..2) {
            if (answerPosition != positionCorrectAnswer) {
                answerList.add(
                    getAnswer(
                        moviePosition = fakeMoviePositions[fakeMoviePositionIndex],
                        isCorrect = false
                    )
                )
                fakeMoviePositionIndex++
            } else {
                answerList.add(
                    getAnswer(moviePosition = moviePosition, isCorrect = true)
                )
            }
        }

        return answerList
    }*/
}