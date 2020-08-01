package com.studio.sevenapp.android.data.user

import com.studio.sevenapp.android.domain.user.UserRepository
import org.koin.core.module.Module

fun Module.insertUserRepository(){
    factory<UserRepository>{
        UserRepositoryImpl(get(), get())
    }
}