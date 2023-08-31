package com.example.currencyconverter.data.data_source.network

import android.content.Context
import android.net.ConnectivityManager
import com.example.currencyconverter.presentation.AppClass
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor : Interceptor {
    private val context = AppClass.appContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw NoInternetException("No Internet Available")

        return chain.proceed(chain.request())
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return (it != null) && it.isConnected
        }
    }
}
