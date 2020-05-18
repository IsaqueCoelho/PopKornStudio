package com.studio.sevenapp.android.domain.userusecase

import org.koin.core.module.Module

fun Module.insertUserUseCase() {
    factory<UserUseCase> {
        UserUseCaseImpl(get())
    }
}