package com.studio.sevenapp.android.data.news

import com.studio.sevenapp.android.domain.news.NewsRepository
import org.koin.core.module.Module

fun Module.insertNewsDependencies(){
    single<NewsRepository> {
        NewsRepositoryImpl(get())
    }
}