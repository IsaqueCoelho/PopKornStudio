package com.studio.sevenapp.android.data.di

import com.studio.sevenapp.android.data.challenge.insertChallengeRepository
import com.studio.sevenapp.android.data.infra.insertInfra
import com.studio.sevenapp.android.data.moviegenre.insertMovieGenreRepository
import com.studio.sevenapp.android.data.news.insertNewsDependencies
import com.studio.sevenapp.android.data.remoteconfig.insertRemoteConfigRepositoryDependencies
import com.studio.sevenapp.android.data.user.insertUserRepository
import org.koin.dsl.module

val dataModule = module {

    // infra
    insertInfra()

    // User
    insertUserRepository()

    // Genre
    insertMovieGenreRepository()

    // Challenge
    insertChallengeRepository()

    // News
    insertNewsDependencies()

    // RemoteConfig
    insertRemoteConfigRepositoryDependencies()

}