package com.studio.sevenapp.android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuestionEntity(
    @PrimaryKey val id: String,
    val challengeId: String,
    val topic: String,
    val context: String
)