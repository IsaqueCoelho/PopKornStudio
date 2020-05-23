package com.studio.sevenapp.android.domain.moviegenreusecase

import com.studio.sevenapp.android.domain.model.MovieGenre

interface MovieGenreUseCase {
    suspend fun getMovieCategories() : List<MovieGenre>
}