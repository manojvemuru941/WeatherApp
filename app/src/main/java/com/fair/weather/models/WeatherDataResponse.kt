package com.fair.weather.models

import com.google.gson.annotations.SerializedName

data class WeatherDataResponse(
    @SerializedName("id")
    val id: Long,

    @SerializedName("weather_state_name")
    val weatherStateName: String,

    @SerializedName("weather_state_abbr")
    val weatherStateAbbr: String,

    @SerializedName("applicable_date")
    val applicableDate: String,

    @SerializedName("min_temp")
    val minTemp: Float,

    @SerializedName("max_temp")
    val maxTemp: Float,

    @SerializedName("the_temp")
    val theTemp: Float,

    @SerializedName("humidity")
    val humidity: Int,
)
