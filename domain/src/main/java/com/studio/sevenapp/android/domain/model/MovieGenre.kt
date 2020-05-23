package com.studio.sevenapp.android.domain.model

import com.google.gson.annotations.SerializedName

data class MovieGenre (
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String? = null
)