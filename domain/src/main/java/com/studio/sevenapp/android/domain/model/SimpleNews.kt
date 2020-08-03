package com.studio.sevenapp.android.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class SimpleNews(
    val title: String,
    val description: String
) : News(), Parcelable