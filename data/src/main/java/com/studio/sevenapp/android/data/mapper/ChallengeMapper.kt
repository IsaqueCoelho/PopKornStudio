package com.studio.sevenapp.android.data.mapper

import com.studio.sevenapp.android.data.model.ChallengeEntity
import com.studio.sevenapp.android.domain.base.BaseMapper
import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.Question

class ChallengeMapper(
    private val questioList: List<Question> = emptyList()
) : BaseMapper<Challenge, ChallengeEntity>() {

    override fun transformToEntity(dataObject: Challenge): ChallengeEntity {
        return ChallengeEntity(
            id = dataObject.id,
            genre = dataObject.genre
        )
    }

    override fun transformFromEntity(entityObject: ChallengeEntity): Challenge {
        return Challenge(
            id = entityObject.id,
            genre = entityObject.genre,
            questionList = questioList
        )
    }
}