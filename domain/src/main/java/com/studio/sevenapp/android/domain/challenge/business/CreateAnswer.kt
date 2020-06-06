package com.studio.sevenapp.android.domain.challenge.business

import com.studio.sevenapp.android.domain.model.Answer
import com.studio.sevenapp.android.domain.model.Movie
import java.text.SimpleDateFormat
import java.util.*

class CreateAnswer {

    private lateinit var movie: Movie

    fun create(movie: Movie, questionMovieId: Int, questionType: QuestionTypeEnum): Answer {
        this.movie = movie

        return Answer(
            id = UUID.randomUUID().toString(),
            isCorrect = checkIsCorrect(questionMovieId),
            isChecked = false,
            text = getTextAnswer(questionType)
        )
    }

    private fun checkIsCorrect(questionMovieId: Int): Boolean {
        return movie.id == questionMovieId
    }

    private fun getTextAnswer(questionType: QuestionTypeEnum): String {
        return when (questionType) {
            QuestionTypeEnum.OVERVIEW -> {
                movie.title
            }
            else -> {
                getDateFormated(movie.release_date)
            }
        }
    }

    private fun getDateFormated(releaseDate: String): String {
        val parser = SimpleDateFormat(ORIGINAL_DATE_FORMAT, Locale.ENGLISH)
        val formatter = SimpleDateFormat(FINAL_DATE_FORMAT, Locale.ENGLISH)
        return formatter.format(parser.parse(releaseDate))
    }

    companion object{
        private const val ORIGINAL_DATE_FORMAT = "yyyy-MM-dd"
        private const val FINAL_DATE_FORMAT = "dd/MM/yyyy"
    }
}