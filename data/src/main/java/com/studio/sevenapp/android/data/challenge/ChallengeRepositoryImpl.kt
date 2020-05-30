package com.studio.sevenapp.android.data.challenge

import com.studio.sevenapp.android.domain.challenge.ChallengeRepository
import com.studio.sevenapp.android.domain.model.Movie

class ChallengeRepositoryImpl(
    private val movieRemoteSource: MovieRemoteSource
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
}