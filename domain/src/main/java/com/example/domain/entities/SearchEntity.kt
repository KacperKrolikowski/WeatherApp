package com.example.domain.entities

data class SearchEntity(
    val id: String,
    val name: String,
    val region: String,
    val country: String,
    val isFromHistory: Boolean = false
)
