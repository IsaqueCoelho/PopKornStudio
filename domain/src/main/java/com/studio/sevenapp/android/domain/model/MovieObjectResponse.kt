package com.studio.sevenapp.android.domain.model

import com.google.gson.annotations.SerializedName
import com.studio.sevenapp.android.domain.model.Movie

data class MovieObjectResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movieList: List<Movie>
)