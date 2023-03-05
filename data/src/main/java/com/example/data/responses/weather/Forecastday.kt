package com.example.data.responses.weather


import com.google.gson.annotations.SerializedName

data class Forecastday(
    @SerializedName("astro")
    val astroResponse: AstroResponse,
    @SerializedName("date")
    val date: String,
    @SerializedName("date_epoch")
    val dateEpoch: Int,
    @SerializedName("day")
    val dayResponse: DayResponse,
    @SerializedName("hour")
    val hour: List<HourResponse>
)