package com.studio.sevenapp.android.data.challenge.localsource

import com.studio.sevenapp.android.data.mapper.AnswerMapper
import com.studio.sevenapp.android.data.mapper.ChallengeMapper
import com.studio.sevenapp.android.data.mapper.QuestionMapper
import com.studio.sevenapp.android.data.model.*
import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.Question

class ChallengeLocalSourceImpl(
    private val challengeDao: ChallengeDao,
    private val challengeMapper: ChallengeMapper,
    private val questionMapper: QuestionMapper,
    private val answerMapper: AnswerMapper
) : ChallengeLocalSource {

    override suspend fun getChallengeByGenre(genre: String): Challenge? {
        var challenge: Challenge? = null
        val questionList: List<Question>

        val challengeQuery: ChallengeWithQuestionWithAnswer? =
            challengeDao.getChallengeByGenre(genre = genre)

        if (challengeQuery != null) {
            questionList =
                getQuestionList(questionWithAnswerList = challengeQuery.questionWithAnswerList)
            challenge =
                challengeMapper.transformFromEntity(
                    entityObject = challengeQuery.challengeEntity,
                    questions = questionList
                )
        }

        return challenge
    }

    override suspend fun getQuestionsByState(state: String): List<Question> {
        val questionWithAnswerList: List<QuestionWithAnswer> =
            challengeDao.getQuestionsByState(state)
        return getQuestionList(questionWithAnswerList = questionWithAnswerList)
    }

    override suspend fun insertChallenge(challenge: Challenge) {
        val challengeEntity: ChallengeEntity =
            challengeMapper.transformToEntity(dataObject = challenge)
        val questionEntityList: List<QuestionEntity> = questionMapper.transformListToEntity(
            challengeId = challenge.id,
            questionList = challenge.questionList
        )
        val answerEntityList: List<AnswerEntity> =
            getAnswerEntityList(questionList = challenge.questionList)

        challengeDao.insertNewChallenge(
            challengeEntity = challengeEntity,
            questionEntityList = questionEntityList,
            answerEntityList = answerEntityList
        )
    }

    override suspend fun updateQuestion(question: Question) {
        challengeDao.updateQuestion(
            questionId = question.id,
            state = question.state.name,
            isCorrect = question.isCorrect
        )
    }

    override suspend fun updateChallenge(challenge: Challenge) {
        val questionEntityList: List<QuestionEntity> = questionMapper.transformListToEntity(
            challengeId = challenge.id,
            questionList = challenge.questionList
        )

        challengeDao.updateChallenge(
            challengeId = challenge.id,
            division = challenge.division.name
        )

        challengeDao.updateQuestionList(
            questionEntityList = questionEntityList
        )
    }

    override suspend fun deleteData(challenge: Challenge) {
        val challengeEntity: ChallengeEntity =
            challengeMapper.transformToEntity(dataObject = challenge)
        val questionEntityList: List<QuestionEntity> = questionMapper.transformListToEntity(
            challengeId = challenge.id,
            questionList = challenge.questionList
        )
        val answerEntityList: List<AnswerEntity> =
            getAnswerEntityList(questionList = challenge.questionList)

        challengeDao.deleteChallenge(
            challengeEntity = challengeEntity,
            questionEntityList = questionEntityList,
            answerEntityList = answerEntityList
        )
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

    private fun getAnswerEntityList(questionList: List<Question>): List<AnswerEntity> {
        val answerEntityList = mutableListOf<AnswerEntity>()

        questionList.forEach { question ->
            answerEntityList.addAll(
                answerMapper.transformListToEntity(
                    question.answerList,
                    question.id
                )
            )
        }

        return answerEntityList
    }
}