package com.studio.sevenapp.android.data.infra

import com.studio.sevenapp.android.data.BuildConfig

class BuildInfoHelper {

    /* Apis Info ====== */

    val webApiUrl: String
        get() = BuildConfig.WEB_API_URL
}