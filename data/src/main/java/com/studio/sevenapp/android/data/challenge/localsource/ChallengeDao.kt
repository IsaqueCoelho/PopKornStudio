package com.studio.sevenapp.android.data.challenge.localsource

import androidx.room.*
import com.studio.sevenapp.android.data.model.*

@Dao
interface ChallengeDao {

    @Transaction
    @Query("SELECT * FROM ChallengeEntity WHERE genre = :genre")
    suspend fun getChallengeByGenre(genre: String): ChallengeWithQuestionWithAnswer

    @Transaction
    @Query("SELECT * FROM QuestionEntity WHERE state = :state")
    suspend fun getQuestionsByState(state: String): List<QuestionWithAnswer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewChallenge(
        challengeEntity: ChallengeEntity,
        questionEntityList: List<QuestionEntity>,
        answerEntityList: List<AnswerEntity>
    )

    @Query("UPDATE QuestionEntity SET state = :state , is_correct = :isCorrect WHERE id = :questionId")
    suspend fun updateQuestion(questionId: String, state: String, isCorrect: Boolean)

    @Query("UPDATE ChallengeEntity SET division = :division WHERE id = :challengeId")
    suspend fun updateChallenge(challengeId: String, division: String)

    @Update
    suspend fun updateQuestionList(questionEntityList: List<QuestionEntity>)

    @Delete
    suspend fun deleteChallenge(
        challengeEntity: ChallengeEntity,
        questionEntityList: List<QuestionEntity>,
        answerEntityList: List<AnswerEntity>
    )
}