package com.example.domain.repositories

import com.example.domain.entities.SearchEntity

interface WeatherApiRepository {
    suspend fun getSearchResult(query: String): Result<List<SearchEntity>>
}