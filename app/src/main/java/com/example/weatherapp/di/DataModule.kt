package com.example.weatherapp.di

import com.example.data.WeatherAPI
import com.example.data.database.SearchedPlacesDao
import com.example.data.repositories.WeatherApiRepositoryImpl
import com.example.data.repositories.WeatherDatabaseRepositoryImpl
import com.example.domain.repositories.WeatherApiRepository
import com.example.domain.repositories.WeatherDatabaseRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideWeatherApiRepository(
        api: WeatherAPI
    ): WeatherApiRepository = WeatherApiRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideDatabaseRepository(
        searchedPlacesDao: SearchedPlacesDao
    ) : WeatherDatabaseRepository = WeatherDatabaseRepositoryImpl(searchedPlacesDao)
}