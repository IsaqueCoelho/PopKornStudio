package com.studio.sevenapp.android.domain.moviegenre

import org.koin.core.module.Module

fun Module.insertMovieGenreUseCase(){
    factory<MovieGenreUseCase> {
        MovieGenreUseCaseImpl(get())
    }
}