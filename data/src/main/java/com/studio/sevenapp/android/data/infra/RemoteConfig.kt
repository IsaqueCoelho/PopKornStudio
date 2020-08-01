package com.studio.sevenapp.android.data.infra

interface RemoteConfig {
    fun getString(key: String): String
    suspend fun refreshRemoteConfig()
}