package com.studio.sevenapp.android.firebase.remoteconfig

import com.crashlytics.android.Crashlytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.studio.sevenapp.android.data.infra.RemoteConfig

class RemoteConfigImpl(
    private val remoteConfig: FirebaseRemoteConfig
) : RemoteConfig {

    override fun getString(key: String): String {
        return remoteConfig.getString(key)
    }

    override suspend fun refreshRemoteConfig() {
        remoteConfig.fetchAndActivate().addOnFailureListener { exception ->
            Crashlytics.logException(exception)
        }
    }
}