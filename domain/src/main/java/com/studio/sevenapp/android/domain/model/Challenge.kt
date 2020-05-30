package com.studio.sevenapp.android.domain.model

data class Challenge(
    val id: String,
    val genreName: String,
    val challengeQuestionList: List<ChallengeQuestion>
)