package com.studio.sevenapp.android.data.challenge.mapper

import com.studio.sevenapp.android.data.model.QuestionEntity
import com.studio.sevenapp.android.domain.base.BaseMapper
import com.studio.sevenapp.android.domain.model.Answer
import com.studio.sevenapp.android.domain.model.Question

class QuestionMapper : BaseMapper<Question, QuestionEntity>() {

    private var challengeId: String = ""
    private var answerList: List<Answer> = emptyList()

    override fun transformToEntity(dataObject: Question): QuestionEntity {
        return QuestionEntity(
            id = dataObject.id,
            topic = dataObject.topic,
            context = dataObject.context,
            challengeId = challengeId
        )
    }

    override fun transformFromEntity(entityObject: QuestionEntity): Question {
        return Question(
            id = entityObject.id,
            topic = entityObject.topic,
            context = entityObject.context,
            answerList = answerList
        )
    }

    fun transformFromEntity(entityObject: QuestionEntity, answerList: List<Answer>): Question {
        this.answerList = answerList
        return transformFromEntity(entityObject)
    }

    fun transformListToEntity(objectList: List<Question>, challengeId: String): List<QuestionEntity> {
        this.challengeId = challengeId
        return transformListToEntity(objectList)
    }
}