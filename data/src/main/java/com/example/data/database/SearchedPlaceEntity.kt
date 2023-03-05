package com.example.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.entities.SearchEntity

@Entity(tableName = "searchResults")
data class SearchedPlaceEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "latLngId") val id: String,
    val name: String,
    val region: String,
    val country: String
) {
    fun toEntity() = SearchEntity(
        id = this.id,
        name = this.name,
        region = this.region,
        country = this.country,
        isFromHistory = true
    )
}
