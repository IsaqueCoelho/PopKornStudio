package com.studio.sevenapp.android.data.moviegenrerepository

import com.studio.sevenapp.android.domain.model.MovieGenre
import com.studio.sevenapp.android.domain.moviegenreusecase.MovieGenreRepository

class MovieGenreRepositoryImpl(
    private val movieGenreRemoteSource: MovieGenreRemoteSource
) :
    MovieGenreRepository {
    override suspend fun getMovieCategories(): List<MovieGenre>? {
        return movieGenreRemoteSource.getCategoryMovies().genres
    }
}