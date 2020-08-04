package com.studio.sevenapp.android.data.infra

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val KOIN_DB_NAME = "db_name"
const val KOIN_WEB_API_URL = "webApiUrl"
const val KOIN_RETROFIT = "retrofit"
const val KOIN_TMDB_TOKEN = "tmdb_token"
const val KOIN_TMDB_LANGUAGE = "tmdb_language"
const val KOIN_IS_DEBUG = "debug_mode"
const val KOIN_OKHTTP = "okhttp"

const val TOKEN_PARAM = "api_key"
const val LANGUAGE_PARAM = "language"

fun Module.insertInfra() {

    single {
        BuildInfoHelper()
    }

    single(named(KOIN_DB_NAME)) {
        "c-${get<BuildInfoHelper>().productName.hashCode()}-db"
    }

    single(named(KOIN_WEB_API_URL)) {
        get<BuildInfoHelper>().webApiUrl
    }

    single(named(KOIN_TMDB_TOKEN)) {
        get<BuildInfoHelper>().webApiToken
    }

    single(named(KOIN_TMDB_LANGUAGE)) {
        get<BuildInfoHelper>().webApiLanguage
    }

    single(named(KOIN_IS_DEBUG)) {
        get<BuildInfoHelper>().isDebug
    }

    // Gson dependencies
    single {
        Gson()
    }

    single {
        GsonConverterFactory.create(get<Gson>())
    }

    // Request interception
    single {
        ErrorInterceptor(isLoggingEnabled = get(named(KOIN_IS_DEBUG)))
    }

    single {
        HttpLoggingInterceptor().apply {
            level =
                if (get(named(KOIN_IS_DEBUG))) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
        }
    }

    single {
        Interceptor { chain ->
            val newUrl = chain.request().url
                .newBuilder()
                .addQueryParameter(TOKEN_PARAM, get<String>(named(KOIN_TMDB_TOKEN)))
                .addQueryParameter(LANGUAGE_PARAM, get<String>(named(KOIN_TMDB_LANGUAGE)))
                .build()

            val newResquest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newResquest)
        }
    }

    single(named(KOIN_OKHTTP)) {
        OkHttpClient().newBuilder()
            .addInterceptor(get<Interceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    // Retrofit Injection
    single(named(KOIN_RETROFIT)) {
        Retrofit
            .Builder()
            .baseUrl(get<String>(named(KOIN_WEB_API_URL)))
            .addConverterFactory(get<GsonConverterFactory>())
            .client(get(named(KOIN_OKHTTP)))
            .build()
    }

    // Local Create Database
    single {
        createRoomDb<AppDatabase>(get(), get(named(KOIN_DB_NAME)))
    }

}