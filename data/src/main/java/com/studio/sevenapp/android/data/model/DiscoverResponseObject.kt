package com.studio.sevenapp.android.data.model

import com.google.gson.annotations.SerializedName
import com.studio.sevenapp.android.domain.model.Movie

data class DiscoverResponseObject(
    @SerializedName("results") val movieList: List<Movie>
)