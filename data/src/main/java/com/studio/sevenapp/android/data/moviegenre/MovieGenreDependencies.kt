package com.studio.sevenapp.android.data.moviegenre

import com.studio.sevenapp.android.data.infra.ServiceFactory
import com.studio.sevenapp.android.domain.moviegenre.MovieGenreRepository
import org.koin.core.module.Module
import org.koin.core.qualifier.named

const val KOIN_RETROFIT = "retrofit"

fun Module.insertMovieGenreRepository(){

    single {
        ServiceFactory.createService(get(named(KOIN_RETROFIT)), MovieGenreRemoteSource::class.java)
    }

    single<MovieGenreRepository>{
        MovieGenreRepositoryImpl(get())
    }

}