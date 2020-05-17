package com.studio.sevenapp.android.popkornstudio.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.studio.sevenapp.android.popkornstudio.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel() {

    private val shouldChangeScreenLv = MutableLiveData<Boolean>()

    fun shouldChangeScreen() = shouldChangeScreenLv

    init {
        viewModelScope.launch {
            delay(3000)
            shouldChangeScreenLv.postValue(true)
        }
    }

}