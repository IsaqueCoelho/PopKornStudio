package com.studio.sevenapp.android.data.challenge

import com.studio.sevenapp.android.data.challenge.localsource.ChallengeLocalSource
import com.studio.sevenapp.android.data.challenge.localsource.ChallengeLocalSourceImpl
import com.studio.sevenapp.android.data.challenge.remotesource.MovieRemoteSource
import com.studio.sevenapp.android.data.infra.AppDatabase
import com.studio.sevenapp.android.data.infra.ServiceFactory
import com.studio.sevenapp.android.data.mapper.AnswerMapper
import com.studio.sevenapp.android.data.mapper.ChallengeMapper
import com.studio.sevenapp.android.data.mapper.QuestionMapper
import com.studio.sevenapp.android.data.moviegenre.KOIN_RETROFIT
import com.studio.sevenapp.android.domain.challenge.ChallengeRepository
import org.koin.core.module.Module
import org.koin.core.qualifier.named

fun Module.insertChallengeRepository() {

    // Remote injection
    single {
        ServiceFactory.createService(get(named(KOIN_RETROFIT)), MovieRemoteSource::class.java)
    }

    single {
        get<AppDatabase>().challengeDao()
    }

    // Mappers
    single {
        ChallengeMapper()
    }

    single {
        QuestionMapper()
    }

    single {
        AnswerMapper()
    }

    // Local Source
    single<ChallengeLocalSource> {
        ChallengeLocalSourceImpl(get(), get(), get(), get())
    }

    // Repository
    single<ChallengeRepository> {
        ChallengeRepositoryImpl(get(), get(), get(), get())
    }

}