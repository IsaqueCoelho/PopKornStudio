package com.studio.sevenapp.android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuestionEntity(
    @PrimaryKey val id: String,
    val topic: String,
    val context: String,
    val movieId: Int,
    val type: String,
    val state: String,
    val is_correct: Boolean,
    val time: String
)