package com.studio.sevenapp.android.data.challenge.localsource

import androidx.room.*
import com.studio.sevenapp.android.data.model.AnswerEntity
import com.studio.sevenapp.android.data.model.QuestionEntity
import com.studio.sevenapp.android.data.model.QuestionWithAnswer

@Dao
interface ChallengeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestionWithAnswer(
        questionEntityList: List<QuestionEntity>,
        answerEntityList: List<AnswerEntity>
    )

    @Update
    suspend fun updatedAnswer(answerEntity: AnswerEntity)

    @Transaction
    @Query("SELECT * FROM QuestionEntity WHERE state = :state")
    suspend fun getQuestionsByState(state: String): List<QuestionWithAnswer>
}