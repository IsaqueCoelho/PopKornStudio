package com.studio.sevenapp.android.data.model

import androidx.room.Embedded
import androidx.room.Relation

class ChallengeWithQuestionWithAnswer(
    @Embedded val challengeEntity: ChallengeEntity,
    @Relation(
        entity = QuestionEntity::class,
        parentColumn = "id",
        entityColumn = "challengeId"
    ) val questionWithAnswerList: List<QuestionWithAnswer>
)