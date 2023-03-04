package com.example.domain.usecases

import com.example.domain.entities.SearchEntity
import com.example.domain.repositories.WeatherDatabaseRepository
import javax.inject.Inject

class SavePlaceUseCase @Inject constructor(
    private val weatherDatabaseRepository: WeatherDatabaseRepository
) {
    suspend fun execute(searchEntity: SearchEntity) =
        weatherDatabaseRepository.savePlace(searchEntity)
}