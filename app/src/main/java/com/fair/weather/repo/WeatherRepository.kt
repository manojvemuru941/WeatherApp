package com.fair.weather.repo

import com.fair.weather.api.WeatherAPI
import com.fair.weather.di.ApiModule
import com.fair.weather.models.ConsolidatedWeatherResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(private var weatherAPI: WeatherAPI) {

    /**
     * Action func to generate weather API data
     *
     * @param cityName
     * @return
     */
    suspend fun getWeatherByCity(cityName: String): Flow<ConsolidatedWeatherResponse> {
        return flow { emit(weatherAPI.getWeatherByCode(cityCode = cityName)) }
    }
}
