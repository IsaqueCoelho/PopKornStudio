package com.studio.sevenapp.android.data.mapper

import com.studio.sevenapp.android.data.model.QuestionEntity
import com.studio.sevenapp.android.domain.base.BaseMapper
import com.studio.sevenapp.android.domain.challenge.business.QuestionStateEnum
import com.studio.sevenapp.android.domain.challenge.business.QuestionTypeEnum
import com.studio.sevenapp.android.domain.model.Answer
import com.studio.sevenapp.android.domain.model.Question

class QuestionMapper : BaseMapper<Question, QuestionEntity>() {

    private lateinit var challengeId: String
    private var answerList: List<Answer> = emptyList()

    override fun transformToEntity(dataObject: Question): QuestionEntity {
        return QuestionEntity(
            id = dataObject.id,
            challengeId = challengeId,
            topic = dataObject.topic,
            context = dataObject.context,
            movieId = dataObject.movieId,
            type = dataObject.type.name,
            state = dataObject.state.name,
            is_correct = dataObject.isCorrect,
            time = dataObject.time
        )
    }

    override fun transformFromEntity(entityObject: QuestionEntity): Question {
        return Question(
            id = entityObject.id,
            topic = entityObject.topic,
            context = entityObject.context,
            movieId = entityObject.movieId,
            type = getQuestionType(entityObject.type),
            state = getQuestionState(entityObject.state),
            isCorrect = entityObject.is_correct,
            time = entityObject.time,
            answerList = answerList
        )
    }

    fun transformListToEntity(challengeId: String, questionList: List<Question>): List<QuestionEntity> {
        this.challengeId = challengeId
        return transformListToEntity(objectList = questionList)
    }

    fun transformFromEntity(entityObject: QuestionEntity, answerList: List<Answer>): Question {
        this.answerList = answerList
        return transformFromEntity(entityObject)
    }

    private fun getQuestionType(type: String): QuestionTypeEnum {
        return when (type) {
            QuestionTypeEnum.OVERVIEW.name -> {
                QuestionTypeEnum.OVERVIEW
            }
            else -> QuestionTypeEnum.RELEASE_DATE
        }
    }

    private fun getQuestionState(type: String): QuestionStateEnum {
        return when (type) {
            QuestionStateEnum.AVAILABLE.name -> {
                QuestionStateEnum.AVAILABLE
            }
            QuestionStateEnum.TO_VALIDATE.name -> {
                QuestionStateEnum.TO_VALIDATE
            }
            else -> QuestionStateEnum.RESOLVED
        }
    }
}