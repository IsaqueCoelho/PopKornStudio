package com.studio.sevenapp.android.data.mapper

import com.studio.sevenapp.android.data.model.QuestionEntity
import com.studio.sevenapp.android.domain.base.BaseMapper
import com.studio.sevenapp.android.domain.challenge.business.QuestionStateEnum
import com.studio.sevenapp.android.domain.challenge.business.QuestionTypeEnum
import com.studio.sevenapp.android.domain.model.Answer
import com.studio.sevenapp.android.domain.model.Question

class QuestionMapper : BaseMapper<Question, QuestionEntity>() {

    private var answerList: List<Answer> = emptyList()

    override fun transformToEntity(dataObject: Question): QuestionEntity {
        return QuestionEntity(
            id = dataObject.id,
            topic = dataObject.topic,
            context = dataObject.context,
            movieId = dataObject.movieId,
            type = dataObject.type.name,
            state = dataObject.state.name,
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
            time = entityObject.time,
            answerList = answerList
        )
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