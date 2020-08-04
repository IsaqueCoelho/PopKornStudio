package com.studio.sevenapp.android.popkornstudio.base

import androidx.annotation.CallSuper
import androidx.lifecycle.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.named

abstract class BaseViewModel : ViewModel(), KoinComponent {
    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onViewResumed() {
    }

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onViewStoped() {
    }

    protected val loadStateLv = MutableLiveData<Boolean>()
    fun showLoading(): LiveData<Boolean> = loadStateLv

    protected val mustShowToastLv = MutableLiveData<Pair<Boolean, Int>>()
    fun showToast(): LiveData<Pair<Boolean, Int>> = mustShowToastLv

    private val showNoInternetConnectionLv by inject<MutableLiveData<Boolean>>(
        named(CONNECTIVITY_LIVE_DATA)
    )

    fun showNoInternetConnection(): LiveData<Boolean> = showNoInternetConnectionLv

}