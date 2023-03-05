package com.example.weatherapp.ui.home

import com.example.domain.entities.WeatherData
import com.example.weatherapp.base.BaseViewState

sealed class HomeViewState : BaseViewState {
    data class Success(val results: WeatherData): HomeViewState()
    object Loading : HomeViewState()
    object Error : HomeViewState()
}