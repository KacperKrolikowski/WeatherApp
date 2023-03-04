package com.example.data.utils

import com.example.data.database.SearchedPlaceEntity
import com.example.domain.entities.SearchEntity

fun SearchEntity.toDatabaseEntity() = SearchedPlaceEntity(
    id = this.id,
    name = this.name,
    region = this.region,
    country = this.country
)