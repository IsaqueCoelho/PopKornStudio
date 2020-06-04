package com.studio.sevenapp.android.domain.challenge.business

import com.studio.sevenapp.android.domain.model.Answer
import com.studio.sevenapp.android.domain.model.Movie
import com.studio.sevenapp.android.domain.model.Question

class GenerateChallengeDataLists(
    private val movieList: List<Movie>
) {

    fun getQuestionList(): List<Question> {
        val questionList = mutableListOf<Question>()
        val createQuestion = CreateQuestion()

        movieList.forEach { movieToQuestion ->
            val question = createQuestion.create(movieToQuestion)

            question.answerList = getAnswerList(question)
            questionList.add(question)
        }

        return questionList
    }

    private fun getAnswerList(question: Question): List<Answer> {
        val answerList = mutableListOf<Answer>()
        val createAnswer = CreateAnswer()

        movieList.forEach { movie ->
            answerList.add(
                createAnswer.create(
                    movie = movie,
                    questionMovieId = question.movieId,
                    questionType = question.type
                )
            )
        }
        return answerList
    }
}