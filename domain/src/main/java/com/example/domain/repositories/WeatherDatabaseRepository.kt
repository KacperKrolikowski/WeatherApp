package com.example.domain.repositories

import com.example.domain.entities.SearchEntity

interface WeatherDatabaseRepository {
    suspend fun savePlace(searchEntity: SearchEntity)
    suspend fun deleteSavedPlace(searchEntity: SearchEntity)
    suspend fun getSavedPlaces(): List<SearchEntity>
}