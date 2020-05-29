package com.studio.sevenapp.android.domain.challenge

import com.studio.sevenapp.android.domain.model.MovieObjectResponse

interface ChallengeRepository {
    suspend fun getMoviesByGenre(page: Int = 1, genre: Int) : MovieObjectResponse
}