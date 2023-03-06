package com.example.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchedPlacesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePlace(searchedPlaceEntity: SearchedPlaceEntity)

    @Delete
    suspend fun deletePlace(searchedPlaceEntity: SearchedPlaceEntity)

    @Query("SELECT * from searchResults")
    fun getSaved(): Flow<List<SearchedPlaceEntity>>
}