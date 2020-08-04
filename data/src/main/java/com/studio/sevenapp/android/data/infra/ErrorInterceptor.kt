package com.studio.sevenapp.android.data.infra

import android.util.Log
import com.studio.sevenapp.android.domain.base.error.ApiException
import com.studio.sevenapp.android.domain.base.error.AuthorizationException
import com.studio.sevenapp.android.domain.base.error.NotFoundException
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.KoinComponent

class ErrorInterceptor(
    private val isLoggingEnabled: Boolean
) : Interceptor, KoinComponent {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        return chain.proceed(request).apply {
            if (!isSuccessful) {
                Log.e(
                    ErrorInterceptor::class.java.simpleName,
                    "Error received from ${request.url}: $code"
                )

                throw when (code) {
                    AuthorizationException.code -> AuthorizationException
                    NotFoundException.code -> NotFoundException
                    else -> ApiException(code, body?.string() ?: message)
                }.printStackTrace(isLoggingEnabled)
            }
        }
    }
}