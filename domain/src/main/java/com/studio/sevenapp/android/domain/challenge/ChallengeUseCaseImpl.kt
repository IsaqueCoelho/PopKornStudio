package com.studio.sevenapp.android.domain.challenge

import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.MovieGenre

class ChallengeUseCaseImpl(
    private val challengeRepository: ChallengeRepository
) : ChallengeUseCase {
    override suspend fun getChallenge(genre: MovieGenre): Challenge {
        val movieList = challengeRepository.getMoviesByGenre(genre = genre.id)
        return CreateChallengeMovie(movieList).generateChallenge(genre.name!!)
    }
}