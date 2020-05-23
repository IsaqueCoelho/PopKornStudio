package com.studio.sevenapp.android.data.moviegenrerepository

import com.google.gson.annotations.SerializedName
import com.studio.sevenapp.android.domain.model.MovieGenre

data class MovieGenreObjectResponse(
    @SerializedName("genres") val genres: List<MovieGenre>
)