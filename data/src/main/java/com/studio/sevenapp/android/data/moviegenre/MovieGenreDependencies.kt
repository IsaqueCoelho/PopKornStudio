package com.studio.sevenapp.android.data.moviegenre

import com.studio.sevenapp.android.data.infra.AppDatabase
import com.studio.sevenapp.android.data.infra.KOIN_DB_NAME
import com.studio.sevenapp.android.data.infra.ServiceFactory
import com.studio.sevenapp.android.data.infra.createRoomDb
import com.studio.sevenapp.android.data.mapper.GenreMapper
import com.studio.sevenapp.android.data.moviegenre.localsource.GenreLocalSource
import com.studio.sevenapp.android.data.moviegenre.localsource.GenreLocalSourceImpl
import com.studio.sevenapp.android.domain.moviegenre.MovieGenreRepository
import org.koin.core.module.Module
import org.koin.core.qualifier.named

const val KOIN_RETROFIT = "retrofit"

fun Module.insertMovieGenreRepository(){

    single {
        ServiceFactory.createService(get(named(KOIN_RETROFIT)), MovieGenreRemoteSource::class.java)
    }

    // Local injection
    single {
        get<AppDatabase>().genreDao()
    }

    // Mapper
    single {
        GenreMapper()
    }

    // Local Source
    single<GenreLocalSource>{
        GenreLocalSourceImpl(get(), get())
    }

    // Repository
    single<MovieGenreRepository>{
        MovieGenreRepositoryImpl(get(), get())
    }

}