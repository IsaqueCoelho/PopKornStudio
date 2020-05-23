package com.studio.sevenapp.android.data.userrepositoy

import com.studio.sevenapp.android.domain.userusecase.UserRepository
import com.studio.sevenapp.android.firebase.authentication.FirebaseAuthenticationImpl
import org.koin.core.module.Module

fun Module.insertUserRepository(){

    single {
        FirebaseAuthenticationImpl(get())
    }
    single<UserRepository>{
        UserRepositoryImpl(get())
    }
}