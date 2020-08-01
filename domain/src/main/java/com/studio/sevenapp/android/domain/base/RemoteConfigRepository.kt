package com.studio.sevenapp.android.domain.base

interface RemoteConfigRepository {
    fun getString(key: String): String
    suspend fun refreshRemoteConfigData()
}