package com.studio.sevenapp.android.data.challenge

import com.studio.sevenapp.android.data.challenge.localsource.ChallengeLocalSource
import com.studio.sevenapp.android.data.challenge.remotesource.MovieRemoteSource
import com.studio.sevenapp.android.domain.challenge.ChallengeRepository
import com.studio.sevenapp.android.domain.challenge.business.QuestionStateEnum
import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.Movie
import com.studio.sevenapp.android.domain.model.Question

class ChallengeRepositoryImpl(
    private val movieRemoteSource: MovieRemoteSource,
    private val challengeLocalSource: ChallengeLocalSource
) : ChallengeRepository {

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

    override suspend fun getChallengeByGenre(genre: String): Challenge? {
        val challenge: Challenge? = challengeLocalSource.getChallengeByGenre(genre = genre)

        challenge?.let {
            it.apply {
                questionList = questionList.filter { question ->
                    question.state == QuestionStateEnum.AVAILABLE
                }
            }
        }

        return challenge
    }

    override suspend fun getQuestionsByState(state: QuestionStateEnum): List<Question> {
        return challengeLocalSource.getQuestionsByState(state = state.name)
    }

    override suspend fun insertChallenge(challenge: Challenge) =
        challengeLocalSource.insertChallenge(challenge = challenge)

    override suspend fun updateQuestion(question: Question) =
        challengeLocalSource.updateQuestion(question = question)

    override suspend fun deleteData(challenge: Challenge) =
        challengeLocalSource.deleteData(challenge = challenge)
}