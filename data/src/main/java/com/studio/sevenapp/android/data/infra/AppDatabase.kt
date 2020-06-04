package com.studio.sevenapp.android.data.infra

import androidx.room.Database
import androidx.room.RoomDatabase
import com.studio.sevenapp.android.data.model.AnswerEntity
import com.studio.sevenapp.android.data.model.ChallengeEntity
import com.studio.sevenapp.android.data.challenge.localsource.ChallengeDao
import com.studio.sevenapp.android.data.model.QuestionEntity

@Database(
    entities = [ChallengeEntity::class, QuestionEntity::class, AnswerEntity::class],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun challengeDao(): ChallengeDao
}