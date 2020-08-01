package com.studio.sevenapp.android.domain.di

import com.studio.sevenapp.android.domain.challenge.insertChallenge
import com.studio.sevenapp.android.domain.moviegenre.insertMovieGenreUseCase
import com.studio.sevenapp.android.domain.news.insertNews
import com.studio.sevenapp.android.domain.user.insertUserUseCase
import org.koin.dsl.module

val domainModule = module {

    // User
    insertUserUseCase()

    // Movie Genre
    insertMovieGenreUseCase()

    // Challenge
    insertChallenge()

    // News
    insertNews()
}