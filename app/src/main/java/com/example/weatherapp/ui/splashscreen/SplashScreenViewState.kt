package com.example.weatherapp.ui.splashscreen

import com.example.weatherapp.base.BaseViewState

sealed class SplashScreenViewState : BaseViewState {
    data class Success(val id: String) : SplashScreenViewState()
}