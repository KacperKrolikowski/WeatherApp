package com.example.data.responses.weather


import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("current")
    val currentResponse: CurrentResponse,
    @SerializedName("forecast")
    val forecastResponse: ForecastResponse,
    @SerializedName("location")
    val locationResponse: LocationResponse
)