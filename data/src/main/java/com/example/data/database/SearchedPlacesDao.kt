package com.example.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SearchedPlacesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePlace(searchedPlaceEntity: SearchedPlaceEntity)

    @Delete
    suspend fun deletePlace(searchedPlaceEntity: SearchedPlaceEntity)

    @Query("SELECT * from searchResults")
    suspend fun getSaved() : List<SearchedPlaceEntity>
}