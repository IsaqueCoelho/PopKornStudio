package com.studio.sevenapp.android.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class QuestionWithAnswer(
    @Embedded val questionEntity: QuestionEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "questionId"
    ) val answerEntityList: List<AnswerEntity>
)