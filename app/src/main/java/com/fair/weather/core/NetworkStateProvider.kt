package com.fair.weather.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object NetworkStateProvider {

    fun getNetworkState(): Boolean {
        val connectivityManager: ConnectivityManager =
            WeatherApp.getContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        return networkCapabilities != null && (networkCapabilities.hasTransport(
            NetworkCapabilities.TRANSPORT_WIFI
        ) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || networkCapabilities.hasTransport(
            NetworkCapabilities.TRANSPORT_ETHERNET
        ) || networkCapabilities.hasTransport(
            NetworkCapabilities.TRANSPORT_BLUETOOTH
        ))
    }
}