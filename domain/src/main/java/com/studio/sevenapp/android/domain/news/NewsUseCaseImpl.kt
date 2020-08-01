package com.studio.sevenapp.android.domain.news

import android.util.Log
import com.studio.sevenapp.android.domain.model.News

class NewsUseCaseImpl(
    private val newsRepository: NewsRepository
): NewsUseCase {
    override fun getNews(): News {
        val json = newsRepository.getNews(key = NewsEnum.NEWS.name)

        Log.e(NewsUseCaseImpl::class.java.simpleName, json)
        return News()
    }
}