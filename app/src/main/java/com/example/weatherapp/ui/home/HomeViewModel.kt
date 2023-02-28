package com.example.weatherapp.ui.home

import com.example.weatherapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): BaseViewModel<HomeViewEvent, HomeViewState>() {
    override fun onViewEvent(viewEvent: HomeViewEvent) {

    }
}