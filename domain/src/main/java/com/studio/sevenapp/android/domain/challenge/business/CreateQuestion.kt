package com.studio.sevenapp.android.domain.challenge.business

import com.studio.sevenapp.android.domain.model.Movie
import com.studio.sevenapp.android.domain.model.Question
import java.util.*

class CreateQuestion {

    private lateinit var movie: Movie

    fun create(movie: Movie): Question {
        this.movie = movie
        val questionType = getRandomType()

        return Question(
            id = UUID.randomUUID().toString(),
            topic = getQuestionTopic(questionType),
            context = getQuestionContext(questionType),
            movieId = movie.id,
            type = questionType,
            state = QuestionStateEnum.AVAILABLE,
            isCorrect = false,
            time = getTime(questionType),
            answerList = emptyList()
        )
    }

    private fun getTime(questionType: QuestionTypeEnum): String {
        return when (questionType) {
            QuestionTypeEnum.OVERVIEW -> {
                "30000"
            }
            else -> {
                "16000"
            }
        }
    }

    private fun getRandomType(): QuestionTypeEnum {
        return QuestionTypeEnum.values()[
                (QuestionTypeEnum.values().indices).random()
        ]
    }

    private fun getQuestionTopic(questionType: QuestionTypeEnum): String {
        return when (questionType) {
            QuestionTypeEnum.OVERVIEW -> {
                "Qual o filme da sinopse:"
            }
            else -> {
                "Quando lançou o filme:"
            }
        }
    }

    private fun getQuestionContext(questionType: QuestionTypeEnum): String {
        return when (questionType) {
            QuestionTypeEnum.OVERVIEW -> {
                movie.overview
            }
            else -> {
                movie.title
            }
        }
    }
}