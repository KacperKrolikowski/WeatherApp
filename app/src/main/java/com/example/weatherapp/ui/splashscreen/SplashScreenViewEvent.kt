package com.example.weatherapp.ui.splashscreen

import com.example.weatherapp.base.BaseViewEvent

sealed class SplashScreenViewEvent : BaseViewEvent {
    object GetData : SplashScreenViewEvent()
}