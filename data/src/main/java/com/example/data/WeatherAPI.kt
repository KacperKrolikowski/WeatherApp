package com.example.data

import com.example.data.responses.search.SearchItemResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("v1/search.json")
    suspend fun getSearchPropositions(
        @Query(QUERY) query: String
    ): List<SearchItemResponse>

    companion object {
        private const val QUERY = "q"

        const val CACHE_CONTROL_HEADER = "Cache-Control"
        const val CACHE_CONTROL_NO_CACHE = "no-cache"
    }
}