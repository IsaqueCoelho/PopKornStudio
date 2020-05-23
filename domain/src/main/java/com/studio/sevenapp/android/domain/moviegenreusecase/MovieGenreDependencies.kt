package com.studio.sevenapp.android.domain.moviegenreusecase

import org.koin.core.module.Module

fun Module.insertMovieGenreUseCase(){
    factory<MovieGenreUseCase> {
        MovieGenreUseCaseImpl(get())
    }
}