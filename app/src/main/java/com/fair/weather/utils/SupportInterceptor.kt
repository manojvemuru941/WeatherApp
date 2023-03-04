package com.fair.weather.utils

import com.fair.weather.core.NetworkStateProvider
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class SupportInterceptor : Interceptor {

    /**
     * Interceptor class for setting of the headers for every request
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkStateProvider.getNetworkState()) {
            throw NoConnectivityException()
        } else {
            var request = chain.request()
            request = request.newBuilder().addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json").build()
            return chain.proceed(request)
        }
    }
}

class NoConnectivityException : IOException() {
    override val message: String
        get() = "No network available, please check your WiFi or Data connection"
}
