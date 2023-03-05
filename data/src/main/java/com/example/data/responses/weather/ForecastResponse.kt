package com.example.data.responses.weather


import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("forecastday")
    val forecastday: List<Forecastday>
)