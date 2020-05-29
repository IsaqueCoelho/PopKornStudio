package com.studio.sevenapp.android.domain.challenge

import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.MovieObjectResponse

class ChallengeUseCaseImpl(
    private val challengeRepository: ChallengeRepository
) : ChallengeUseCase {
    override suspend fun getChallenge(genre: Int): Challenge {
        val movieObjectResponse = challengeRepository.getMoviesByGenre(genre = genre)
        return createChallenge(movieObjectResponse)
    }

    private fun createChallenge(movieObjectResponse: MovieObjectResponse): Challenge {
        val movieList = movieObjectResponse.movieList
        val createChallenge = CreateChallengeMovie(movieList)
        return createChallenge.generateChallenge()
    }
}