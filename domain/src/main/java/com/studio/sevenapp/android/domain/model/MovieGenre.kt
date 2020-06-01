package com.studio.sevenapp.android.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieGenre (
    val id: Int = 0,
    val name: String? = null
): Parcelable