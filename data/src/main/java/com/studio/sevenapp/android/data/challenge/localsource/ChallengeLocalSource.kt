package com.studio.sevenapp.android.data.challenge.localsource

import com.studio.sevenapp.android.domain.model.Answer
import com.studio.sevenapp.android.domain.model.Challenge

interface ChallengeLocalSource {
    suspend fun getChalengeById(challengeId: String): Challenge
    suspend fun insertChallenge(challenge: Challenge)
    suspend fun updatedAnswer(answer: Answer)
}