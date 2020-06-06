package com.studio.sevenapp.android.data.moviegenre.localsource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.studio.sevenapp.android.data.model.GenreEntity

@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenreList(
        genreEntityList: List<GenreEntity>
    )

    @Query("SELECT * FROM GenreEntity")
    suspend fun getGenreList(): List<GenreEntity>

}