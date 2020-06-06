package com.studio.sevenapp.android.domain.moviegenre

import com.studio.sevenapp.android.domain.model.Genre

interface MovieGenreRepository {
    suspend fun getMovieCategories() : List<Genre>?
}