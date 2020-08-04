package com.studio.sevenapp.android.domain.news

interface NewsRepository {
    fun getNews(key: String): String
    suspend fun refreshNews()
}