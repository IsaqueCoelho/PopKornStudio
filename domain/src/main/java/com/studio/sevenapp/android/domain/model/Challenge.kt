package com.studio.sevenapp.android.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Challenge(
    val id: String,
    val challengeQuestionList: List<ChallengeQuestion>
): Parcelable