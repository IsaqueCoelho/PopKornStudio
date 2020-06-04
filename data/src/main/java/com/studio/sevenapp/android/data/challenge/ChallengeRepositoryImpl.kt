package com.studio.sevenapp.android.data.challenge

import com.studio.sevenapp.android.data.challenge.localsource.ChallengeLocalSource
import com.studio.sevenapp.android.data.challenge.remotesource.MovieRemoteSource
import com.studio.sevenapp.android.domain.challenge.ChallengeRepository
import com.studio.sevenapp.android.domain.challenge.business.QuestionStateEnum
import com.studio.sevenapp.android.domain.model.Answer
import com.studio.sevenapp.android.domain.model.Movie
import com.studio.sevenapp.android.domain.model.Question

class ChallengeRepositoryImpl(
    private val movieRemoteSource: MovieRemoteSource,
    private val challengeLocalSource: ChallengeLocalSource
) : ChallengeRepository {

    override suspend fun insertQuestions(questionList: List<Question>) =
        challengeLocalSource.insertQuestions(questionList = questionList)

    override suspend fun getMoviesByGenre(page: Int, genre: Int): List<Movie> {
        val sortBy = "popularity.desc"
        val includeAdult = false

        return movieRemoteSource.getMovies(
            sortBy = sortBy,
            includeAdult = includeAdult,
            page = page,
            withGenres = genre
        ).movieList
    }

    override suspend fun updatedAnswer(answer: Answer) =
        challengeLocalSource.updatedAnswer(answer = answer)

    override suspend fun getQuestionsByState(state: QuestionStateEnum): List<Question> {
        return challengeLocalSource.getQuestionsByState(state = state.name)
    }
}