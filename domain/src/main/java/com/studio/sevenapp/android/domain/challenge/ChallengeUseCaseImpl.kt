package com.studio.sevenapp.android.domain.challenge

import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.MovieGenre

class ChallengeUseCaseImpl(
    private val challengeRepository: ChallengeRepository
) : ChallengeUseCase {
    override suspend fun getChallenge(genre: MovieGenre): Challenge {
        val movieList = challengeRepository.getMoviesByGenre(genre = genre.id)
        val challenge = CreateChallengeMovie().generateChallenge(
            genreName = genre.name!!,
            movieList = movieList
        )
        challengeRepository.insertChallenge(challenge = challenge)
        return challenge
    }

    override suspend fun getChallengeById(challengeId: String): Challenge {
        return challengeRepository.getChallengeById(challengeId = challengeId)
    }
}