package com.studio.sevenapp.android.domain.challenge

import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.ChallengeResult
import com.studio.sevenapp.android.domain.model.Genre
import com.studio.sevenapp.android.domain.model.Question

interface ChallengeUseCase {
    suspend fun getChallenged(genre: Genre): Challenge
    suspend fun saveQuestion(question: Question)
    suspend fun getChallengeResult(genre: String): ChallengeResult
}