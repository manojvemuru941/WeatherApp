package com.fair.weather.di

import com.fair.weather.api.WeatherAPI
import com.fair.weather.core.AppConstants
import com.fair.weather.utils.SupportInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(AppConstants.BASE_API_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().serializeNulls().setLenient().create()
                )
            )
        retrofitBuilder.client(OkHttpClient.Builder().addInterceptor(SupportInterceptor()).build())
        return retrofitBuilder.build()
    }

    @Singleton
    @Provides
    fun provideWeatherApi(retrofit: Retrofit): WeatherAPI = retrofit.create(WeatherAPI::class.java)
}