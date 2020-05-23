package com.studio.sevenapp.android.data.di

import com.studio.sevenapp.android.data.infra.insertInfra
import com.studio.sevenapp.android.data.moviegenrerepository.insertMovieGenreDependencies
import com.studio.sevenapp.android.data.userrepositoy.insertUserRepository
import org.koin.dsl.module

val dataModule = module {

    // infra
    insertInfra()

    // User Repository
    insertUserRepository()

    // Genre Repository
    insertMovieGenreDependencies()

}