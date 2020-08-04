package com.studio.sevenapp.android.popkornstudio.base

import androidx.lifecycle.MutableLiveData
import org.koin.core.module.Module
import org.koin.core.qualifier.named

const val CONNECTIVITY_LIVE_DATA = "connectivityLiveData"

fun Module.insertBaseDependencies() {

    single(named(CONNECTIVITY_LIVE_DATA)) {
        MutableLiveData<Boolean>()
    }

    single {
        ConnectivityReceiver()
    }

    single {
        EmptyViewModel()
    }
}