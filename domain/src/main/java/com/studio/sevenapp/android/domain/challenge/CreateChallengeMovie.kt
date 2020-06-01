package com.studio.sevenapp.android.domain.challenge

import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.Question
import com.studio.sevenapp.android.domain.model.Answer
import com.studio.sevenapp.android.domain.model.Movie
import java.util.*

class CreateChallengeMovie {

    private var movieList: List<Movie> = emptyList()
    private var questionNumber: Int = 0

    fun generateChallenge(
        genreName: String,
        movieList: List<Movie>
    ): Challenge {
        this.movieList = movieList
        return Challenge(
            id = UUID.randomUUID().toString(),
            genre = genreName,
            questionList = generateQuestions()
        )
    }

    private fun generateQuestions(): List<Question> {
        val questionList: MutableList<Question> = mutableListOf()

        for (position in movieList.indices step 2) {
            questionList.add(
                getQuestionByIndex(position)
            )
        }

        return questionList
    }

    private fun getQuestionByIndex(position: Int): Question {
        return Question(
            id = UUID.randomUUID().toString(),
            topic = getTopic(),
            context = movieList[position].overview,
            answerList = getAnswerOptions(position)
        )
    }

    private fun getTopic(): String {
        questionNumber++
        return "$questionNumber. Qual o Filme da sinopse a seguir?"
    }

    private fun getAnswerOptions(moviePosition: Int): List<Answer> {
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
    }

    private fun getAnswer(moviePosition: Int, isCorrect: Boolean): Answer {
        return Answer(
            id = UUID.randomUUID().toString(),
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