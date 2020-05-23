package com.studio.sevenapp.android.data.moviegenrerepository

import com.studio.sevenapp.android.data.infra.ServiceFactory
import com.studio.sevenapp.android.domain.moviegenreusecase.MovieGenreRepository
import org.koin.core.module.Module
import org.koin.core.qualifier.named

const val KOIN_RETROFIT = "retrofit"

fun Module.insertMovieGenreDependencies(){

    single {
        ServiceFactory.createService(get(named(KOIN_RETROFIT)), MovieGenreRemoteSource::class.java)
    }

    single<MovieGenreRepository>{
        MovieGenreRepositoryImpl(get())
    }

}