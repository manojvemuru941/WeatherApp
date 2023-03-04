package com.fair.weather

import com.fair.weather.api.WeatherAPI
import com.fair.weather.repo.WeatherRepository
import com.fair.weather.usecase.CityWeatherUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class CityWeatherUseCaseTest {

    private lateinit var weatherAPI: WeatherAPI
    private lateinit var weatherRepository: WeatherRepository
    private lateinit var weatherUseCase: CityWeatherUseCase


    @Before
    fun setup() {
        weatherAPI = mock(WeatherAPI::class.java)
        weatherRepository = WeatherRepository(weatherAPI)
        weatherUseCase = CityWeatherUseCase(weatherRepository)
    }

    @Test
    fun `fetch weather data and then return weather UI data`() {
        runBlocking {
            whenever(weatherAPI.getWeatherByCode(cityCode = TestData.CITY_NAME)).thenReturn(TestData.testWeatherData)
            val weatherUIData = weatherUseCase.getAction(cityName = TestData.CITY_NAME)

            weatherUIData.collect {
                Assert.assertTrue(it == TestData.testWeatherUIData)
            }
        }
    }

    @Test
    fun `failed fetch weather data and then return error`() {
        runBlocking {
            try {
                whenever(weatherAPI.getWeatherByCode(cityCode = TestData.CITY_NAME)).thenReturn(
                    TestData.testWeatherEmptyData
                )
                weatherUseCase.getAction(cityName = TestData.CITY_NAME)

            } catch(ex : Exception) {
                Assert.assertTrue(ex.localizedMessage.isNotEmpty())
            }
        }
    }
}