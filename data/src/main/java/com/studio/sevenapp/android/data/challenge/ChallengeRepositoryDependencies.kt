package com.studio.sevenapp.android.data.challenge

import com.studio.sevenapp.android.data.challenge.localsource.ChallengeLocalSource
import com.studio.sevenapp.android.data.challenge.localsource.ChallengeLocalSourceImpl
import com.studio.sevenapp.android.data.challenge.mapper.AnswerMapper
import com.studio.sevenapp.android.data.challenge.mapper.ChallengeMapper
import com.studio.sevenapp.android.data.challenge.mapper.QuestionMapper
import com.studio.sevenapp.android.data.challenge.remotesource.MovieRemoteSource
import com.studio.sevenapp.android.data.infra.AppDatabase
import com.studio.sevenapp.android.data.infra.KOIN_DB_NAME
import com.studio.sevenapp.android.data.infra.ServiceFactory
import com.studio.sevenapp.android.data.infra.createRoomDb
import com.studio.sevenapp.android.data.moviegenre.KOIN_RETROFIT
import com.studio.sevenapp.android.domain.challenge.ChallengeRepository
import org.koin.core.module.Module
import org.koin.core.qualifier.named

fun Module.insertChallengeRepository(){

    // Remote injection
    single {
        ServiceFactory.createService(get(named(KOIN_RETROFIT)), MovieRemoteSource::class.java)
    }

    // Local injections
    single {
        createRoomDb<AppDatabase>(get(), get(named(KOIN_DB_NAME)))
    }

    single {
        get<AppDatabase>().challengeDao()
    }

    single {
        ChallengeMapper()
    }

    single {
        QuestionMapper()
    }

    single {
        AnswerMapper()
    }

    single<ChallengeLocalSource>{
        ChallengeLocalSourceImpl(get(), get(), get(), get())
    }

    // Repository
    single<ChallengeRepository>{
        ChallengeRepositoryImpl(get(), get())
    }

}