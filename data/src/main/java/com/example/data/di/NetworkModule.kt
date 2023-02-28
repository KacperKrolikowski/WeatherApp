package com.example.data.di

import com.example.data.WeatherAPI
import com.example.data.utils.ApiKeyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ApiKeyInterceptor())
            .connectTimeout(CONNECTION_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .readTimeout(READ_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .build()

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory
        .create()

    @Singleton
    @Provides
    fun provideWeatherApiRetrofit(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(WEATHER_API_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()

    @Singleton
    @Provides
    fun provideWeatherApi(retrofit: Retrofit): WeatherAPI =
        retrofit.create(
            WeatherAPI::class.java
        )

    companion object {
        const val WEATHER_API_URL = "https://api.weatherapi.com"
        const val CONNECTION_TIMEOUT_MS = 15000L
        const val READ_TIMEOUT_MS = 15000L
    }
}