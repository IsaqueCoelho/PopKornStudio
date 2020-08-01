package com.studio.sevenapp.android.data.remoteconfig

import com.studio.sevenapp.android.data.infra.RemoteConfig
import com.studio.sevenapp.android.domain.base.RemoteConfigRepository

class RemoteConfigRepositoryImpl(
    private val remoteConfig: RemoteConfig
) : RemoteConfigRepository {

    override fun getString(key: String): String = remoteConfig.getString(key = key)

    override suspend fun refreshRemoteConfigData() {
        remoteConfig.refreshRemoteConfig()
    }
}