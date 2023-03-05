package com.example.domain.repositories

import com.example.domain.entities.SearchEntity
import com.example.domain.entities.WeatherData

interface WeatherApiRepository {
    suspend fun getSearchResult(query: String): Result<List<SearchEntity>>
    suspend fun getWeather(id: String): Result<WeatherData>
}