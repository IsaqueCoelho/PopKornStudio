package com.studio.sevenapp.android.data.challenge.mapper

import com.studio.sevenapp.android.data.model.AnswerEntity
import com.studio.sevenapp.android.domain.base.BaseMapper
import com.studio.sevenapp.android.domain.model.Answer
import java.util.*

class AnswerMapper : BaseMapper<Answer, AnswerEntity>() {

    private var questionId: String = ""

    override fun transformToEntity(dataObject: Answer): AnswerEntity {
        return AnswerEntity(
            id = UUID.randomUUID().toString(),
            text = dataObject.text,
            isCorrect = dataObject.isCorrect,
            isChecked = dataObject.isChecked,
            questionId = questionId
        )
    }

    override fun transformFromEntity(entityObject: AnswerEntity): Answer {
        return Answer(
            text = entityObject.text,
            isChecked = entityObject.isChecked,
            isCorrect = entityObject.isCorrect
        )
    }

    fun transformListToEntity(objectList: List<Answer>, questionId: String): List<AnswerEntity> {
        this.questionId = questionId
        return transformListToEntity(objectList)
    }
}