package com.example.data.repositories

import com.example.data.WeatherAPI
import com.example.domain.entities.CurrentWeatherEntity
import com.example.domain.entities.FutureWeatherEntity
import com.example.domain.entities.SearchEntity
import com.example.domain.entities.WeatherData
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

    override suspend fun getWeather(id: String): Result<WeatherData> {
        val weatherResults = runCatching {
            api.getWeatherData(id)
        }.onFailure {
            Timber.e(it)
        }

        return weatherResults.mapCatching { response ->
            with(response) {
                WeatherData(
                    current = CurrentWeatherEntity(
                        temperature = currentResponse.tempC,
                        location = locationResponse.name,
                        pressure = currentResponse.pressureMb,
                        feelsLikeTemperature = currentResponse.feelslikeC,
                        windDirection = currentResponse.windDir,
                        windSpeed = currentResponse.windKph,
                        weatherIconUrl = "https://${
                            currentResponse.conditionResponse.icon.drop(
                                2
                            )
                        }"
                    ),
                    future = forecastResponse.forecastday.map { forecastDay ->
                        with(forecastDay) {
                            FutureWeatherEntity(
                                date = date,
                                avgTemperature = dayResponse.avgtempC,
                                maxTemperature = dayResponse.maxtempC,
                                minTemperature = dayResponse.mintempC,
                                conditionImageUrl = "https://${
                                    dayResponse.conditionResponse.icon.drop(
                                        2
                                    )
                                }"
                            )
                        }
                    }
                )
            }
        }.onFailure {
            Timber.e(it)
        }
    }
}