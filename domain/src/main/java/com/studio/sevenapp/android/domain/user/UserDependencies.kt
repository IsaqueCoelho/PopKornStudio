package com.studio.sevenapp.android.domain.user

import org.koin.core.module.Module

fun Module.insertUserUseCase() {
    factory<UserUseCase> {
        UserUseCaseImpl(get(), get())
    }
}