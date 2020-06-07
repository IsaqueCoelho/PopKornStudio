package com.studio.sevenapp.android.data.infra

import androidx.room.Database
import androidx.room.RoomDatabase
import com.studio.sevenapp.android.data.model.AnswerEntity
import com.studio.sevenapp.android.data.model.ChallengeEntity
import com.studio.sevenapp.android.data.challenge.localsource.ChallengeDao
import com.studio.sevenapp.android.data.model.GenreEntity
import com.studio.sevenapp.android.data.model.QuestionEntity
import com.studio.sevenapp.android.data.moviegenre.localsource.GenreDao

@Database(
    entities = [GenreEntity::class, ChallengeEntity::class, QuestionEntity::class, AnswerEntity::class],
    version = 7,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun genreDao(): GenreDao
    abstract fun challengeDao(): ChallengeDao
}