package com.studio.sevenapp.android.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
class SimpleNews(
    val title: String,
    val description: String
) : News(), Parcelable