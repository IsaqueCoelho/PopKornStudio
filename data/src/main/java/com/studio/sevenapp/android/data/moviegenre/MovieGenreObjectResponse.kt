package com.studio.sevenapp.android.data.moviegenre

import com.google.gson.annotations.SerializedName
import com.studio.sevenapp.android.domain.model.Genre

data class MovieGenreObjectResponse(
    @SerializedName("genres") val genres: List<Genre>
)