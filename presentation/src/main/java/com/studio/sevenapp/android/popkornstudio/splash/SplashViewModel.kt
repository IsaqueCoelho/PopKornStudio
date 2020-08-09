package com.studio.sevenapp.android.popkornstudio.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.crashlytics.android.Crashlytics
import com.studio.sevenapp.android.domain.news.NewsUseCase
import com.studio.sevenapp.android.domain.user.UserUseCase
import com.studio.sevenapp.android.popkornstudio.R
import com.studio.sevenapp.android.popkornstudio.base.BaseViewModel
import kotlinx.coroutines.*

class SplashViewModel(
    private val userUseCase: UserUseCase,
    private val newsUseCase: NewsUseCase
) : BaseViewModel() {

    private val scope = CoroutineScope(SupervisorJob())
    private val handler = CoroutineExceptionHandler { _, exception ->
        Crashlytics.logException(exception)
        mustShowToastLv.postValue(Pair(true, R.string.failure_load_news))
    }

    private val shouldChangeScreenLv = MutableLiveData<Boolean>()
    fun shouldChangeScreen(): LiveData<Boolean> = shouldChangeScreenLv

    init {
        scope.launch(handler) {
            newsUseCase.refreshNews()
        }

        scope.launch {
            delay(SPLASH_DELAY)
            checkUserAuthentication()
        }
    }

    private suspend fun checkUserAuthentication() {
        val isAuthenticated = userUseCase.isUserLogged()
        shouldChangeScreenLv.postValue(isAuthenticated)
    }

    companion object {
        private const val SPLASH_DELAY = 2300L
    }

}