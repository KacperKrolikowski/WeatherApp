package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

class SearchedPlacesDatabase {
    @Database(
        entities = [SearchedPlaceEntity::class],
        version = 1,
        exportSchema = false
    )

    abstract class WeatherAppDatabase : RoomDatabase() {
        abstract fun searchPlacesDao(): SearchedPlacesDao
    }
}