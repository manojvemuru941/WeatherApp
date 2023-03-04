package com.fair.weather

import com.fair.weather.api.WeatherAPI
import com.fair.weather.repo.WeatherRepository
import org.junit.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class WeatherRepositoryTest {

    private lateinit var weatherAPI: WeatherAPI
    private lateinit var weatherRepository: WeatherRepository

    @Before
    fun setup() {
        weatherAPI = mock(WeatherAPI::class.java)
        weatherRepository = WeatherRepository(weatherAPI)
    }

    @Test
    fun `fetch weather data main test`() {
        runBlocking {
            whenever(weatherAPI.getWeatherByCode(cityCode = TestData.CITY_NAME)).thenReturn(TestData.testWeatherData)
            val weatherData = weatherRepository.getWeatherByCity(cityName = TestData.CITY_NAME)
            weatherData.collect {
                assertTrue(it.listConsolidatedWeather[0].theTemp == TestData.testWeatherData.listConsolidatedWeather[0].theTemp)
            }
        }
    }

    @Test
    fun `fetch weather data test`() {
        runBlocking {
            whenever(weatherAPI.getWeatherByCode(cityCode = TestData.CITY_NAME)).thenReturn(TestData.testWeatherData)
            val weatherData = weatherRepository.getWeatherByCity(cityName = TestData.CITY_NAME)
            weatherData.collect {
                assertTrue(it.listConsolidatedWeather.isNotEmpty())
            }
        }
    }
}