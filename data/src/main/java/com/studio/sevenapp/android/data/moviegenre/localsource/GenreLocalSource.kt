package com.studio.sevenapp.android.data.moviegenre.localsource

import com.studio.sevenapp.android.domain.model.Genre

interface GenreLocalSource {
    suspend fun insertGenreList(genreList: List<Genre>)
    suspend fun getGenreList(): List<Genre>
}