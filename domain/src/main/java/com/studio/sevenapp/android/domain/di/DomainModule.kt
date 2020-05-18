package com.studio.sevenapp.android.domain.di

import com.studio.sevenapp.android.domain.userusecase.insertUserUseCase
import org.koin.dsl.module

val domainModule = module {

    // User useCase
    insertUserUseCase()
}