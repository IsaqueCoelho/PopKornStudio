package com.studio.sevenapp.android.data.challenge.localsource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.studio.sevenapp.android.data.model.AnswerEntity
import com.studio.sevenapp.android.data.model.ChallengeEntity
import com.studio.sevenapp.android.data.model.ChallengeWithQuestionWithAnswer
import com.studio.sevenapp.android.data.model.QuestionEntity

@Dao
interface ChallengeDao {
    @Transaction
    @Query("SELECT * FROM ChallengeEntity WHERE id = :challengeId")
    suspend fun getChallengeById(challengeId: String): ChallengeWithQuestionWithAnswer

    @Insert
    suspend fun insertChallengeWithQuestionWithAnswer(
        challengeEntity: ChallengeEntity,
        questionEntityList: List<QuestionEntity>,
        answerEntityList: List<AnswerEntity>
    )
}