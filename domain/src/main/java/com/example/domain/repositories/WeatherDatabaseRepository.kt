package com.example.domain.repositories

import com.example.domain.entities.SearchEntity
import kotlinx.coroutines.flow.Flow

interface WeatherDatabaseRepository {
    suspend fun savePlace(searchEntity: SearchEntity)
    suspend fun deleteSavedPlace(searchEntity: SearchEntity)
    fun getSavedPlaces(): Flow<List<SearchEntity>>
}