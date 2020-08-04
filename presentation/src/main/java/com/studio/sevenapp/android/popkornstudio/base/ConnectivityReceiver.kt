package com.studio.sevenapp.android.popkornstudio.base

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.named

class ConnectivityReceiver : KoinComponent {

    private val showNoConnectionSnackbarLv by inject<MutableLiveData<Boolean>>(
        named(CONNECTIVITY_LIVE_DATA)
    )

    @VisibleForTesting
    val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            showNoConnectionSnackbarLv.postValue(false)
        }

        override fun onLost(network: Network) {
            showNoConnectionSnackbarLv.postValue(true)
        }
    }

    fun registerNetworkCallback(context: Context) {
        try {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            @SuppressLint("NewApi")
            if (BuildInfoHelper.androidApiLevel >= Build.VERSION_CODES.N) {
                connectivityManager.registerDefaultNetworkCallback(networkCallback)
            } else {
                val networkRequest = NetworkRequest.Builder().build()
                connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
            }

            showNoConnectionSnackbarLv.postValue(!connectivityManager.isNetworkAvailable)
        } catch (ignore: Exception) {
            showNoConnectionSnackbarLv.postValue(true)
        }

    }

    fun unregisterNetworkCallback(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        connectivityManager?.unregisterNetworkCallback(networkCallback)
    }

    @VisibleForTesting
    val ConnectivityManager.isNetworkAvailable: Boolean
        get() {
            @SuppressLint("NewApi")
            if (BuildInfoHelper.androidApiLevel >= Build.VERSION_CODES.M) {
                val capabilities = getNetworkCapabilities(activeNetwork) ?: return false
                val androidCapabilities = listOf(
                    NetworkCapabilities.TRANSPORT_CELLULAR,
                    NetworkCapabilities.TRANSPORT_WIFI,
                    NetworkCapabilities.TRANSPORT_ETHERNET
                )
                return androidCapabilities.map { capabilities.hasTransport(it) }.any { it }
            } else {
                @Suppress("DEPRECATION")
                return activeNetworkInfo?.isConnected == true
            }
        }

}