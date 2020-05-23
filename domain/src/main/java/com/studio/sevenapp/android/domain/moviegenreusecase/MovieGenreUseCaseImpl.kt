package com.studio.sevenapp.android.domain.moviegenreusecase

import com.studio.sevenapp.android.domain.model.MovieGenre

class MovieGenreUseCaseImpl(
    private val movieGenreRepository: MovieGenreRepository
) : MovieGenreUseCase {
    override suspend fun getMovieCategories(): List<MovieGenre> {
        return movieGenreRepository.getMovieCategories() ?: emptyList()
    }
}