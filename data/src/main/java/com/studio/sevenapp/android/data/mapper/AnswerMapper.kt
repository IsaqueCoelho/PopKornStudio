package com.studio.sevenapp.android.data.mapper

import com.studio.sevenapp.android.data.model.AnswerEntity
import com.studio.sevenapp.android.domain.base.BaseMapper
import com.studio.sevenapp.android.domain.model.Answer

class AnswerMapper : BaseMapper<Answer, AnswerEntity>() {

    private var questionId: String = ""

    override fun transformToEntity(dataObject: Answer): AnswerEntity {
        return AnswerEntity(
            id = dataObject.id,
            text = dataObject.text,
            isCorrect = dataObject.isCorrect,
            questionId = questionId
        )
    }

    override fun transformFromEntity(entityObject: AnswerEntity): Answer {
        return Answer(
            id = entityObject.id,
            text = entityObject.text,
            isCorrect = entityObject.isCorrect
        )
    }

    fun transformListToEntity(objectList: List<Answer>, questionId: String): List<AnswerEntity> {
        this.questionId = questionId
        return transformListToEntity(objectList)
    }
}