package com.studio.sevenapp.android.data.challenge.localsource

import com.studio.sevenapp.android.data.challenge.mapper.AnswerMapper
import com.studio.sevenapp.android.data.challenge.mapper.ChallengeMapper
import com.studio.sevenapp.android.data.challenge.mapper.QuestionMapper
import com.studio.sevenapp.android.data.model.*
import com.studio.sevenapp.android.domain.model.Answer
import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.Question

class ChallengeLocalSourceImpl(
    private val challengeDao: ChallengeDao,
    private val challengeMapper: ChallengeMapper,
    private val questionMapper: QuestionMapper,
    private val answerMapper: AnswerMapper
) : ChallengeLocalSource {

    override suspend fun getChalengeById(challengeId: String): Challenge {
        val challengeQuery: ChallengeWithQuestionWithAnswer =
            challengeDao.getChallengeById(challengeId = challengeId)

        return Challenge(
            id = challengeId,
            genre = challengeQuery.challengeEntity.genre,
            questionList = getQuestionList(challengeQuery.questionWithAnswerList)
        )
    }

    override suspend fun insertChallenge(challenge: Challenge) {
        val challengeEntity: ChallengeEntity = challengeMapper.transformToEntity(challenge)
        val questionEntityList: List<QuestionEntity> = getQuestionEntityList(challenge)
        val answerEntityList: List<AnswerEntity> = getAnswerEntityList(challenge.questionList)

        challengeDao.insertChallengeWithQuestionWithAnswer(
            challengeEntity = challengeEntity,
            questionEntityList = questionEntityList,
            answerEntityList = answerEntityList
        )
    }

    override suspend fun updatedAnswer(answer: Answer) {
        val answerEntity = answerMapper.transformToEntity(answer)
        challengeDao.updatedAnswer(answerEntity = answerEntity)
    }

    private fun getQuestionList(questionWithAnswerList: List<QuestionWithAnswer>): List<Question> {
        val questionList = mutableListOf<Question>()

        questionWithAnswerList.forEach { questionQuery ->
            questionList.add(
                questionMapper.transformFromEntity(
                    entityObject = questionQuery.questionEntity,
                    answerList = answerMapper.transformListFromEntity(questionQuery.answerEntityList)
                )
            )
        }

        return questionList
    }

    private fun getQuestionEntityList(challenge: Challenge): List<QuestionEntity> {
        return questionMapper.transformListToEntity(challenge.questionList, challenge.id)
    }

    private fun getAnswerEntityList(questionList: List<Question>): List<AnswerEntity> {
        var answerEntityList: List<AnswerEntity> = emptyList()

        questionList.forEach { question ->
            answerEntityList = answerMapper.transformListToEntity(
                question.answerList,
                question.id
            )
        }

        return answerEntityList
    }
}