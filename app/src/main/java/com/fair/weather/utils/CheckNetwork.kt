package com.fair.weather.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network

object CheckNetwork {
    var available:Int = 0
        private set

    fun registerNetworkCallback(context: Context) {
        kotlin.runCatching {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

            connectivityManager?.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    available += 1
                }

                override fun onLost(network: Network) {
                    available -= 1
                }
            })
        }
    }
}