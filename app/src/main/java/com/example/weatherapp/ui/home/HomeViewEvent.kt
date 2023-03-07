package com.example.weatherapp.ui.home

import com.example.weatherapp.base.BaseViewEvent

sealed class HomeViewEvent : BaseViewEvent {
    data class GetWeatherData(val id: String): HomeViewEvent()
    object ClearPreviouslyRemember: HomeViewEvent()
}