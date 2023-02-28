package com.example.data.repositories

import com.example.data.WeatherAPI
import com.example.domain.entities.SearchEntity
import com.example.domain.repositories.WeatherApiRepository
import timber.log.Timber

class WeatherApiRepositoryImpl(
    private val api: WeatherAPI
) : WeatherApiRepository {
    override suspend fun getSearchResult(query: String): Result<List<SearchEntity>> {
        val searchResult = runCatching {
            api.getSearchPropositions(query)
        }.onFailure {
            Timber.e(it)
        }

        return searchResult.mapCatching {
            it.map { response ->
                with(response) {
                    SearchEntity("$lat,$lon", name, region, country)
                }
            }
        }.onFailure { Timber.e(it) }
    }
}