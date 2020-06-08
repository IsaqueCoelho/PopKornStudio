package com.studio.sevenapp.android.data.mapper

import com.studio.sevenapp.android.data.model.ChallengeEntity
import com.studio.sevenapp.android.domain.base.BaseMapper
import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.Question

class ChallengeMapper : BaseMapper<Challenge, ChallengeEntity>() {

    private lateinit var questioList: List<Question>

    override fun transformToEntity(dataObject: Challenge): ChallengeEntity {
        return ChallengeEntity(
            id = dataObject.id,
            genre = dataObject.genre,
            level = dataObject.level,
            stage = dataObject.stage
        )
    }

    override fun transformFromEntity(entityObject: ChallengeEntity): Challenge {
        return Challenge(
            id = entityObject.id,
            genre = entityObject.genre,
            level = entityObject.level,
            stage = entityObject.stage,
            questionList = questioList
        )
    }

    fun transformFromEntity(entityObject: ChallengeEntity, questions: List<Question>): Challenge {
        this.questioList = questions
        return transformFromEntity(entityObject = entityObject)
    }
}