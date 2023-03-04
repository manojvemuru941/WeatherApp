package com.fair.weather

import com.fair.weather.models.ConsolidatedWeather
import com.fair.weather.models.WeatherData

object TestConstants {
    private val weatherDescResponse = WeatherData(
        id = 4805883302248448,
        weatherStateName = "Light Rain",
        weatherStateAbbr = "lr",
        applicableDate = "2021-09-13",
        minTemp = 22.0f,
        maxTemp = 44.0f,
        theTemp = 23.0f,
        humidity = 56
    )
    private val weatherDescResponse1 = WeatherData(
        id = 4805883302248448,
        weatherStateName = "Light Rain",
        weatherStateAbbr = "lr",
        applicableDate = "2021-09-16",
        minTemp = 22.0f,
        maxTemp = 44.0f,
        theTemp = 23.0f,
        humidity = 56
    )
    val testWeatherData = ConsolidatedWeather(
        listConsolidatedWeather = listOf(weatherDescResponse, weatherDescResponse1),
        city = "Toronto"
    )
}