package com.studio.sevenapp.android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AnswerEntity(
    @PrimaryKey val id: String,
    val questionId: String,
    val text: String,
    val isCorrect: Boolean,
    val isChecked: Boolean
)