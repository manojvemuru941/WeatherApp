package com.fair.weather.models

import com.fair.weather.core.AppConstants
import com.fair.weather.ui.main.ext.WeatherData
import com.google.gson.annotations.SerializedName

data class ConsolidatedWeatherResponse(
    @SerializedName("consolidated_weather")
    val listConsolidatedWeather: List<WeatherDataResponse>,

    @SerializedName("title")
    val city: String
)


fun ConsolidatedWeatherResponse.toUIData(): WeatherData {
    val todayWeather = listConsolidatedWeather[0]
    return WeatherData(
        cityName = this.city,
        currentTemp = todayWeather.theTemp.toInt(),
        minTemp = todayWeather.minTemp.toInt(),
        maxTemp = todayWeather.maxTemp.toInt(),
        state = todayWeather.weatherStateName,
        stateUrl = AppConstants.WEATHER_STATE_URL.replace(
            AppConstants.WEATHER_STATE_KEY,
            todayWeather.weatherStateAbbr
        )
    )
}