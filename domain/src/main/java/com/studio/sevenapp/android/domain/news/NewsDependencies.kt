package com.studio.sevenapp.android.domain.news

import org.koin.core.module.Module

fun Module.insertNews(){
    factory<NewsUseCase> {
        NewsUseCaseImpl(get())
    }
}