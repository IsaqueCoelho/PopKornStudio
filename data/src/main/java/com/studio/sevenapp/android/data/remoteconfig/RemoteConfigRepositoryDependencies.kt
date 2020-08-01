package com.studio.sevenapp.android.data.remoteconfig

import com.studio.sevenapp.android.domain.base.RemoteConfigRepository
import org.koin.core.module.Module

fun Module.insertRemoteConfigRepositoryDependencies() {
    single<RemoteConfigRepository> {
        RemoteConfigRepositoryImpl(get())
    }
}