package com.studio.sevenapp.android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChallengeEntity(
    @PrimaryKey val id: String,
    val genre: String,
    val genreId: Int,
    var level: Int,
    var division: String
)