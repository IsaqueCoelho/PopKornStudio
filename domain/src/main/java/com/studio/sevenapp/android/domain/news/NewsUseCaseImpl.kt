package com.studio.sevenapp.android.domain.news

import com.google.gson.Gson
import com.studio.sevenapp.android.domain.model.News
import com.studio.sevenapp.android.domain.model.SimpleNews

class NewsUseCaseImpl(
    private val newsRepository: NewsRepository,
    private val gson: Gson
) : NewsUseCase {

    private val news = "NEWS"

    override fun getNews(): News {
        val json = newsRepository.getNews(key = news)
        return createNews(json = json)
    }

    override suspend fun refreshNews() {
        newsRepository.refreshNews()
    }

    private fun createNews(json: String): News {
        val news = gson.fromJson(json, News::class.java)
        return when (news.type) {
            NewsEnum.SIMPLE_NEWS.id -> {
                gson.fromJson(json, SimpleNews::class.java)
            }
            else -> {
                gson.fromJson(json, SimpleNews::class.java)
            }
        }
    }
}