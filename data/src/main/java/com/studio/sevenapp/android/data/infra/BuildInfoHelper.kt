package com.studio.sevenapp.android.data.infra

import com.studio.sevenapp.android.data.BuildConfig

class BuildInfoHelper {

    /* Build ======= */
    val productName = "popkorn-studio"

    /* Apis Info ====== */

    val webApiUrl: String
        get() = BuildConfig.WEB_API_URL

    val webApiToken: String
        get() = BuildConfig.API_TOKEN

    val webApiLanguage: String
        get() = BuildConfig.URL_LANGUAGE_PARAM

    val isDebug: Boolean
        get() = BuildConfig.DEBUG
}