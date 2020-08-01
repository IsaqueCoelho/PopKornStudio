package com.studio.sevenapp.android.firebase.remoteconfig

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.studio.sevenapp.android.data.infra.KOIN_IS_DEBUG
import com.studio.sevenapp.android.data.infra.RemoteConfig
import com.studio.sevenapp.android.firebase.R
import org.koin.core.module.Module
import org.koin.core.qualifier.named

const val KOIN_REMOTE_CONFIG_CACHE_EXPIRATION = "remoteConfigCacheExpiration"

fun Module.insertRemoteConfig() {

    single(named(KOIN_REMOTE_CONFIG_CACHE_EXPIRATION)) {
        // Firebase cache expiration time in seconds
        when {
            get<Boolean>(named(KOIN_IS_DEBUG)) -> 0L
            else -> 28800L
        }
    }

    single {
        remoteConfigSettings {
            minimumFetchIntervalInSeconds = get(named(KOIN_REMOTE_CONFIG_CACHE_EXPIRATION))
        }
    }

    single {
        Firebase.remoteConfig.apply {
            setConfigSettingsAsync(get())
            setDefaultsAsync(R.xml.remote_config_defaults)
        }
    }

    single<RemoteConfig> {
        RemoteConfigImpl(get())
    }
}