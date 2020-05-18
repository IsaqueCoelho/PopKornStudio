package com.studio.sevenapp.android.data.di

import com.studio.sevenapp.android.data.userrepositoy.insertUserRepository
import org.koin.dsl.module


val dataModule = module {

    // User Repository
    insertUserRepository()

}