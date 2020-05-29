package com.studio.sevenapp.android.domain.moviegenre

import com.studio.sevenapp.android.domain.model.MovieGenre

interface MovieGenreRepository {
    suspend fun getMovieCategories() : List<MovieGenre>?
}