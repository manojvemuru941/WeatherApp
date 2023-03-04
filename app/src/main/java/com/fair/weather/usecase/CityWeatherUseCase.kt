package com.fair.weather.usecase

import com.fair.weather.models.toUIData
import com.fair.weather.repo.WeatherRepository
import com.fair.weather.ui.main.ext.WeatherData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CityWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) : BaseUseCase.ParamUseCase<String, Flow<WeatherData>> {

    /**
     * Action func to generate
     *
     * @param cityName
     * @return
     */
    override suspend fun getAction(cityName: String): Flow<WeatherData> {
        return repository.getWeatherByCity(cityName).map {
            it.toUIData()
        }
    }
}