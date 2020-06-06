package com.studio.sevenapp.android.data.challenge.localsource

import com.studio.sevenapp.android.data.mapper.AnswerMapper
import com.studio.sevenapp.android.data.mapper.QuestionMapper
import com.studio.sevenapp.android.data.model.AnswerEntity
import com.studio.sevenapp.android.data.model.QuestionEntity
import com.studio.sevenapp.android.data.model.QuestionWithAnswer
import com.studio.sevenapp.android.domain.model.Question

class ChallengeLocalSourceImpl(
    private val challengeDao: ChallengeDao,
    private val questionMapper: QuestionMapper,
    private val answerMapper: AnswerMapper
) : ChallengeLocalSource {

    override suspend fun insertQuestions(questionList: List<Question>) {
        val questionEntityList: List<QuestionEntity> =
            questionMapper.transformListToEntity(questionList)
        val answerEntityList: List<AnswerEntity> =
            getAnswerEntityList(questionList = questionList)

        challengeDao.insertQuestionWithAnswer(
            questionEntityList = questionEntityList,
            answerEntityList = answerEntityList
        )
    }

    override suspend fun updateQuestion(question: Question) {
        val questionEntity = questionMapper.transformToEntity(dataObject = question)
        challengeDao.updateQuestion(questionEntity = questionEntity)
    }

    override suspend fun getQuestionsByState(state: String): List<Question> {
        val questionWithAnswerList: List<QuestionWithAnswer> =
            challengeDao.getQuestionsByState(state)
        return getQuestionList(questionWithAnswerList = questionWithAnswerList)
    }

    override suspend fun deleteData(questionList: List<Question>) {
        val questionEntityList: List<QuestionEntity> =
            questionMapper.transformListToEntity(questionList)
        val answerEntityList: List<AnswerEntity> =
            getAnswerEntityList(questionList = questionList)

        challengeDao.deleteQuestionWithAnswer(
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