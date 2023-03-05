package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.SearchedPlacesDao
import com.example.data.database.SearchedPlacesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideSearchedPlacesDatabase(@ApplicationContext context: Context): SearchedPlacesDatabase.WeatherAppDatabase {
        return Room.databaseBuilder(
            context,
            SearchedPlacesDatabase.WeatherAppDatabase::class.java,
            WEATHERAPP_DB
        ).build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(newsAppDatabase: SearchedPlacesDatabase.WeatherAppDatabase): SearchedPlacesDao =
        newsAppDatabase.searchPlacesDao()

    companion object {
        private const val WEATHERAPP_DB = "WEATHERAPP_DB"
    }
}