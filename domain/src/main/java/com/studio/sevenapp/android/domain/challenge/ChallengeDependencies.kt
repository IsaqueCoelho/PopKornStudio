package com.studio.sevenapp.android.domain.challenge

import org.koin.core.module.Module

fun Module.insertChallenge(){
    factory<ChallengeUseCase>{
        ChallengeUseCaseImpl(get())
    }
}