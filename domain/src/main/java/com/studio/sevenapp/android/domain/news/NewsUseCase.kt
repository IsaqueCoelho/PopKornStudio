package com.studio.sevenapp.android.domain.news

import com.studio.sevenapp.android.domain.model.News

interface NewsUseCase {
    fun getNews(): News
}