package com.example.domain.entities

data class WeatherData(
    val current: CurrentWeatherEntity,
    val future: List<FutureWeatherEntity>
)
