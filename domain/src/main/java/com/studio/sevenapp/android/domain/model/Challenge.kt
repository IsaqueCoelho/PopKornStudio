package com.studio.sevenapp.android.domain.model

import com.studio.sevenapp.android.domain.challenge.business.ChallengeDivisionEnum

data class Challenge(
    val id: String,
    val genre: String,
    var level: Int,
    var division: ChallengeDivisionEnum,
    var questionList: List<Question>
)