package com.studio.sevenapp.android.domain.challenge

import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.ChallengeQuestion
import com.studio.sevenapp.android.domain.model.ChallengeQuestionAnswerOption
import com.studio.sevenapp.android.domain.model.Movie
import java.util.*

class CreateChallengeMovie(private val movieList: List<Movie>) {

    private var questionNumber: Int = 0

    fun generateChallenge(genreName: String): Challenge {
        return Challenge(
            id = UUID.randomUUID().toString(),
            genreName = genreName,
            challengeQuestionList = generateQuestions()
        )
    }

    private fun generateQuestions(): List<ChallengeQuestion> {
        val questionList: MutableList<ChallengeQuestion> = mutableListOf()

        for (position in movieList.indices step 2) {
            questionList.add(
                getQuestionByIndex(position)
            )
        }

        return questionList
    }

    private fun getQuestionByIndex(position: Int): ChallengeQuestion {
        return ChallengeQuestion(
            questionTopic = getTopic(),
            questionContext = movieList[position].overview,
            questionAnswerOptions = getAnswerOptions(position)
        )
    }

    private fun getTopic(): String {
        questionNumber++
        return "$questionNumber. Qual o Filme da sinopse a seguir?"
    }

    private fun getAnswerOptions(moviePosition: Int): List<ChallengeQuestionAnswerOption> {
        val answerOptionList: MutableList<ChallengeQuestionAnswerOption> = mutableListOf()
        val positionCorrectAnswer = (0..2).random()
        val fakeMoviePositions = getFakeMoviesPositions(moviePosition)
        var fakeMoviePositionIndex = 0

        for (answerPosition in 0..2) {
            if (answerPosition != positionCorrectAnswer) {
                answerOptionList.add(
                    getAnswer(
                        moviePosition = fakeMoviePositions[fakeMoviePositionIndex],
                        isCorrect = false
                    )
                )
                fakeMoviePositionIndex++
            } else {
                answerOptionList.add(
                    getAnswer(moviePosition = moviePosition, isCorrect = true)
                )
            }
        }

        return answerOptionList
    }

    private fun getAnswer(moviePosition: Int, isCorrect: Boolean): ChallengeQuestionAnswerOption {
        return ChallengeQuestionAnswerOption(
            text = movieList[moviePosition].title,
            isCorrect = isCorrect
        )
    }

    private fun getFakeMoviesPositions(moviePosition: Int): List<Int> {
        val fakePositions = mutableListOf<Int>()

        while (fakePositions.size < 2) {
            val fakePosition = (movieList.indices).random()
            if (fakePosition != moviePosition && !fakePositions.contains(fakePosition)) {
                fakePositions.add(fakePosition)
            }
        }
        return fakePositions
    }
}