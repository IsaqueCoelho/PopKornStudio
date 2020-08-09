package com.studio.sevenapp.android.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre (
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String
): Parcelable