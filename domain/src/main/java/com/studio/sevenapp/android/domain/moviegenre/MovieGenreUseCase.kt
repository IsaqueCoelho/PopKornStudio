package com.studio.sevenapp.android.domain.moviegenre

import com.studio.sevenapp.android.domain.model.Genre

interface MovieGenreUseCase {
    suspend fun getMovieCategories() : List<Genre>
}