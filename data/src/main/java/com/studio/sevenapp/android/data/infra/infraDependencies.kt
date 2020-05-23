package com.studio.sevenapp.android.data.infra

import com.google.gson.Gson
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val KOIN_WEB_API_URL = "webApiUrl"
const val KOIN_RETROFIT = "retrofit"

fun Module.insertInfra(){

    single {
        BuildInfoHelper()
    }

    single(named(KOIN_WEB_API_URL)) {
        get<BuildInfoHelper>().webApiUrl
    }

    single {
        Gson()
    }

    single {
        GsonConverterFactory.create(get<Gson>())
    }

    single(named(KOIN_RETROFIT)) {
        Retrofit
            .Builder()
            .baseUrl(get<String>(named(KOIN_WEB_API_URL)))
            .addConverterFactory(get<GsonConverterFactory>())
            .build()
    }
}