package com.studio.sevenapp.android.data.challenge

import com.studio.sevenapp.android.data.infra.ServiceFactory
import com.studio.sevenapp.android.data.moviegenre.KOIN_RETROFIT
import com.studio.sevenapp.android.domain.challenge.ChallengeRepository
import org.koin.core.module.Module
import org.koin.core.qualifier.named

fun Module.insertChallengeRepository(){

    single {
        ServiceFactory.createService(get(named(KOIN_RETROFIT)), MovieRemoteSource::class.java)
    }

    single<ChallengeRepository>{
        ChallengeRepositoryImpl(get())
    }

}