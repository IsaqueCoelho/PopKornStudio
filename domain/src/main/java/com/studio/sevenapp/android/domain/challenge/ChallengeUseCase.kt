package com.studio.sevenapp.android.domain.challenge

import com.studio.sevenapp.android.domain.model.Challenge

interface ChallengeUseCase {
    suspend fun getChallenge(genre: Int) : Challenge
}