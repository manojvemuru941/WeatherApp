package com.fair.weather.core

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object {
        private lateinit var application: Application
        fun getContext(): Context {
            return application.applicationContext
        }
    }
}