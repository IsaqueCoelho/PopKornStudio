package com.studio.sevenapp.android.domain.model

data class Challenge(
    val id: String,
    val genre: String,
    val questionList: List<Question>
)