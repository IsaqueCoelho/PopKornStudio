package com.studio.sevenapp.android.domain.model

data class Challenge(
    val id: String,
    val genre: String,
    var level: Int,
    var stage: String,
    var questionList: List<Question>
)