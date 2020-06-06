package com.studio.sevenapp.android.domain.moviegenre

import com.studio.sevenapp.android.domain.model.Genre

class MovieGenreUseCaseImpl(
    private val movieGenreRepository: MovieGenreRepository
) : MovieGenreUseCase {
    override suspend fun getMovieCategories(): List<Genre> {
        return movieGenreRepository.getMovieCategories() ?: emptyList()
    }
}