package com.example.domain.usecases

import com.example.domain.repositories.WeatherApiRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherApiRepository: WeatherApiRepository
) {
    suspend fun execute(id: String) = weatherApiRepository.getWeather(id)
}