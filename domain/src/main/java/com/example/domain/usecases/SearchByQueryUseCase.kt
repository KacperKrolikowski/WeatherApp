package com.example.domain.usecases

import com.example.domain.repositories.WeatherApiRepository
import javax.inject.Inject

class SearchByQueryUseCase @Inject constructor(
    private val weatherApiRepository: WeatherApiRepository
) {
    suspend fun execute(query: String) = weatherApiRepository.getSearchResult(query)
}