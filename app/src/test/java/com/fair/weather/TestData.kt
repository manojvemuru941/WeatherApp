package com.fair.weather

import com.fair.weather.models.ConsolidatedWeatherResponse
import com.fair.weather.models.WeatherDataResponse
import com.fair.weather.ui.main.model.WeatherData

object TestData {
    const val CITY_NAME = "city_name_test"
    private val weatherDescResponse = WeatherDataResponse(
        id = 4805883302248448,
        weatherStateName = "Light Rain",
        weatherStateAbbr = "lr",
        applicableDate = "2021-09-13",
        minTemp = 22.0f,
        maxTemp = 44.0f,
        theTemp = 23.0f,
        humidity = 56
    )
    private val weatherDescResponse1 = WeatherDataResponse(
        id = 4805883302248448,
        weatherStateName = "Light Rain",
        weatherStateAbbr = "lr",
        applicableDate = "2021-09-16",
        minTemp = 22.0f,
        maxTemp = 44.0f,
        theTemp = 23.0f,
        humidity = 56
    )
    val testWeatherData = ConsolidatedWeatherResponse(
        listConsolidatedWeather = listOf(weatherDescResponse, weatherDescResponse1),
        city = CITY_NAME
    )

    val testWeatherEmptyData = ConsolidatedWeatherResponse(
        listConsolidatedWeather = listOf(),
        city = ""
    )

    val testWeatherUIData = WeatherData(
        cityName = CITY_NAME,
        currentTemp = weatherDescResponse.theTemp.toInt(),
        minTemp = weatherDescResponse.minTemp.toInt(),
        maxTemp = weatherDescResponse.maxTemp.toInt(),
        state = weatherDescResponse.weatherStateName,
        stateUrl = "https://cdn.faire.com/static/mobile-take-home/icons/${weatherDescResponse.weatherStateAbbr}.png"
    )
}