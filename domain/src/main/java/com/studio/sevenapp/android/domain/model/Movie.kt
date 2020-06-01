package com.studio.sevenapp.android.domain.model

data class Movie(
    val id: Int,
    val overview: String,
    val genreIds: List<Int>,
    val title: String,
    val releaseDate: String
)