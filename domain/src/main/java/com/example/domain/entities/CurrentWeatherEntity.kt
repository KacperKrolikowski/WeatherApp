package com.example.domain.entities

data class CurrentWeatherEntity(
    val temperature: Double,
    val location: String,
    val pressure: Double,
    val feelsLikeTemperature: Double,
    val windDirection: String,
    val windSpeed: Double,
    val weatherIconUrl: String
)