package com.studio.sevenapp.android.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChallengeQuestion (
    val id: Int,
    val questionTopic: String,
    val questionContext: String,
    val questionAnswerOptions: List<ChallengeQuestionAnswerOption>
) : Parcelable