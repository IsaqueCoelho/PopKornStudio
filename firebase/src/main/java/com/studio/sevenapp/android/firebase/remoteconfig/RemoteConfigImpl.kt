package com.studio.sevenapp.android.firebase.remoteconfig

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.studio.sevenapp.android.data.infra.RemoteConfig
import kotlinx.coroutines.tasks.await

class RemoteConfigImpl(
    private val remoteConfig: FirebaseRemoteConfig
) : RemoteConfig {

    override fun getString(key: String): String {
        return remoteConfig.getString(key)
    }

    override suspend fun refreshRemoteConfig() {
        remoteConfig.fetchAndActivate().await()
    }
}