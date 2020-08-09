package com.studio.sevenapp.android.domain.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("overview") val overview: String,
    @SerializedName("genreIds") val genreIds: List<Int>,
    @SerializedName("title") val title: String,
    @SerializedName("release_date") val release_date: String
)