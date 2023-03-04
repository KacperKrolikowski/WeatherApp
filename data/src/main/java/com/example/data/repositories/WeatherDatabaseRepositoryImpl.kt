package com.example.data.repositories

import com.example.data.database.SearchedPlacesDao
import com.example.data.utils.toDatabaseEntity
import com.example.domain.entities.SearchEntity
import com.example.domain.repositories.WeatherDatabaseRepository

class WeatherDatabaseRepositoryImpl(
    private val searchedPlacesDao: SearchedPlacesDao
) : WeatherDatabaseRepository {
    override suspend fun savePlace(searchEntity: SearchEntity) {
        searchedPlacesDao.savePlace(searchEntity.toDatabaseEntity())
    }

    override suspend fun deleteSavedPlace(searchEntity: SearchEntity) {
        searchedPlacesDao.deletePlace(searchEntity.toDatabaseEntity())
    }

    override suspend fun getSavedPlaces(): List<SearchEntity> {
        return searchedPlacesDao.getSaved().map {
            it.toEntity()
        }
    }
}