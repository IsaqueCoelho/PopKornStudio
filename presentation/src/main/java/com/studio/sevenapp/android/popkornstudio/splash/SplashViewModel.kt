package com.studio.sevenapp.android.popkornstudio.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.studio.sevenapp.android.domain.user.UserUseCase
import com.studio.sevenapp.android.popkornstudio.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
    private val userUseCase : UserUseCase
) : BaseViewModel() {

    private val shouldChangeScreenLv = MutableLiveData<Boolean>()

    fun shouldChangeScreen(): LiveData<Boolean> = shouldChangeScreenLv

    init {
        viewModelScope.launch {
            delay(SPLASH_DELAY)
            checkUserAuthentication()
        }
    }

    private fun checkUserAuthentication(){
        val isAuthenticated = userUseCase.isUserLogged()
        shouldChangeScreenLv.postValue(isAuthenticated)
    }

    companion object{
        private const val SPLASH_DELAY = 2300L
    }

}