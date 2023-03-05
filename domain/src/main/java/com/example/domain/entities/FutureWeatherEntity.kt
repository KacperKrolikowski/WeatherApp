package com.example.domain.entities

data class FutureWeatherEntity(
    val date: String,
    val avgTemperature: Double,
    val maxTemperature: Double,
    val minTemperature: Double,
    val conditionImageUrl: String
)
