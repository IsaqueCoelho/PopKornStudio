package com.studio.sevenapp.android.popkornstudio.base

import androidx.annotation.CallSuper
import androidx.lifecycle.*
import org.koin.core.KoinComponent

abstract class BaseViewModel : ViewModel(), KoinComponent {
    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onViewResumed() {
    }

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onViewStoped(){
    }

    protected val loadStateLv = MutableLiveData<Boolean>()
    fun showLoading(): LiveData<Boolean> = loadStateLv
}