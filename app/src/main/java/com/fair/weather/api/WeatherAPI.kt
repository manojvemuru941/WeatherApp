package com.fair.weather.api

import com.fair.weather.models.ConsolidatedWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherAPI {

    @GET("static/mobile-take-home/{code}.json")
    suspend fun getWeatherByCode(
        @Path("code")
        cityCode: String
    ): ConsolidatedWeatherResponse
}