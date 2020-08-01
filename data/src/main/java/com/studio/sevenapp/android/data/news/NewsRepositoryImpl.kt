package com.studio.sevenapp.android.data.news

import com.studio.sevenapp.android.domain.base.RemoteConfigRepository
import com.studio.sevenapp.android.domain.news.NewsRepository

class NewsRepositoryImpl(
    private val remoteConfigRepository: RemoteConfigRepository
): NewsRepository {
    override fun getNews(key: String): String {
        return remoteConfigRepository.getString(key = key)
    }
}