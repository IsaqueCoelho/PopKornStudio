package com.studio.sevenapp.android.data.challenge

import com.studio.sevenapp.android.data.challenge.localsource.ChallengeLocalSource
import com.studio.sevenapp.android.data.challenge.remotesource.MovieRemoteSource
import com.studio.sevenapp.android.domain.challenge.ChallengeRepository
import com.studio.sevenapp.android.domain.model.Answer
import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.Movie

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

    override suspend fun getChallengeById(challengeId: String): Challenge =
        challengeLocalSource.getChalengeById(challengeId = challengeId)

    override suspend fun insertChallenge(challenge: Challenge) =
        challengeLocalSource.insertChallenge(challenge = challenge)

    override suspend fun updatedAnswer(answer: Answer) =
        challengeLocalSource.updatedAnswer(answer = answer)
}