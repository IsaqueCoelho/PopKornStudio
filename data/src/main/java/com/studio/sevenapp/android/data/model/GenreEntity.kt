package com.studio.sevenapp.android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GenreEntity(
    @PrimaryKey val id: String,
    val genreId: Int,
    val name: String?
)