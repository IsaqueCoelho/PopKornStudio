package com.studio.sevenapp.android.popkornstudio.features.home

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.studio.sevenapp.android.domain.model.News
import com.studio.sevenapp.android.domain.model.SimpleNews
import com.studio.sevenapp.android.domain.news.NewsUseCase
import com.studio.sevenapp.android.domain.user.UserUseCase
import com.studio.sevenapp.android.popkornstudio.base.BaseViewModel
import com.studio.sevenapp.android.popkornstudio.features.news.SimpleNewsFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userUseCase: UserUseCase,
    private val newsUseCase: NewsUseCase
) : BaseViewModel() {

    private val mustShowUserLv = MutableLiveData<FirebaseUser>()
    fun showUser() : LiveData<FirebaseUser> = mustShowUserLv

    private val mustShowNewsLv = MutableLiveData<Fragment>()
    fun showNews() : LiveData<Fragment> = mustShowNewsLv

    init {
        viewModelScope.launch {
            loadData()
        }
    }

    private suspend fun loadData() {
        mustShowUserLv.postValue(
            userUseCase.getCurrentUser()
        )

        delay(3000)
        mustShowNewsLv.postValue(
            createFragment(news = newsUseCase.getNews())
        )
    }

    private fun createFragment(news: News): Fragment {
        return when(news){
            is SimpleNews -> SimpleNewsFragment.params(news = news)
            else -> SimpleNewsFragment.params(news = news)
        }
    }
}