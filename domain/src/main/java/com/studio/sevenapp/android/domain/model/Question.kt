package com.studio.sevenapp.android.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question (
    val id: String,
    val topic: String,
    val context: String,
    val answerList: List<Answer>
): Parcelable