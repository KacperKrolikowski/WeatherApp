package com.example.data

import com.example.data.responses.search.SearchItemResponse
import com.example.data.responses.weather.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("v1/search.json")
    suspend fun getSearchPropositions(
        @Query(QUERY) query: String
    ): List<SearchItemResponse>

    @GET("v1/forecast.json")
    suspend fun getWeatherData(
        @Query(QUERY) id: String,
        @Query(DAYS_QUERY) days: Int = 8,
        @Query(AQI_QUERY) aqi: String = FALSE_STRING,
        @Query(ALERTS_QUERY) alerts: String = FALSE_STRING
    ): WeatherResponse

    companion object {
        private const val QUERY = "q"
        private const val DAYS_QUERY = "days"
        private const val AQI_QUERY = "aqi"
        private const val ALERTS_QUERY = "alerts"

        private const val FALSE_STRING = "no"

        const val CACHE_CONTROL_HEADER = "Cache-Control"
        const val CACHE_CONTROL_NO_CACHE = "no-cache"
    }
}