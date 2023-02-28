package com.example.domain.usecases

import com.example.domain.entities.SearchEntity
import com.example.domain.repositories.WeatherApiRepository
import javax.inject.Inject

class SearchByQueryUseCase @Inject constructor(private val weatherApiRepository: WeatherApiRepository) {
    suspend fun execute(query: String): List<SearchEntity>? =
        weatherApiRepository.getSearchResult(query).getOrNull()
}