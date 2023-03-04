package com.fair.weather.ui.main.ext

data class WeatherData(
    val cityName: String,
    val currentTemp: Int,
    val minTemp: Int,
    val maxTemp: Int,
    val state: String,
    val stateUrl: String
)
