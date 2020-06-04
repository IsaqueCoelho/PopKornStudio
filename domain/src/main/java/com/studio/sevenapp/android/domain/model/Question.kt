package com.studio.sevenapp.android.domain.model

import android.os.Parcelable
import com.studio.sevenapp.android.domain.challenge.business.QuestionStateEnum
import com.studio.sevenapp.android.domain.challenge.business.QuestionTypeEnum
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question(
    val id: String,
    val topic: String,
    val context: String,
    val movieId: Int,
    val type: QuestionTypeEnum,
    val state: QuestionStateEnum,
    val time: String,
    var answerList: List<Answer>
) : Parcelable