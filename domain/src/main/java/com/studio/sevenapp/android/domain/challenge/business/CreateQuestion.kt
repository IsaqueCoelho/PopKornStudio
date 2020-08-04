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
            time = getTimeInMillis(questionType),
            answerList = emptyList()
        )
    }

    private fun getTimeInMillis(questionType: QuestionTypeEnum): String {
        val thirtySeconds = "30000"
        val sixteenSeconds = "16000"

        return when (questionType) {
            QuestionTypeEnum.OVERVIEW -> {
                thirtySeconds
            }
            else -> {
                sixteenSeconds
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