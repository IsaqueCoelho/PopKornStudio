package com.studio.sevenapp.android.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Answer (
    val id: String,
    val text: String,
    var isCorrect: Boolean = false,
    var isChecked: Boolean = false
):Parcelable