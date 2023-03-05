package com.example.domain.usecases

import com.example.domain.repositories.WeatherDatabaseRepository
import javax.inject.Inject

class GetSavedPlacesUseCase @Inject constructor(
    private val weatherDatabaseRepository: WeatherDatabaseRepository
) {
    suspend fun execute() = weatherDatabaseRepository.getSavedPlaces()
}