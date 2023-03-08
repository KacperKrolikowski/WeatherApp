package com.example.weatherapp.ui.home

import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.ClearDefaultUseCase
import com.example.domain.usecases.GetWeatherUseCase
import com.example.weatherapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val clearDefaultUseCase: ClearDefaultUseCase
) : BaseViewModel<HomeViewEvent, HomeViewState>() {
    override fun onViewEvent(viewEvent: HomeViewEvent) {
        when(viewEvent){
            is HomeViewEvent.GetWeatherData -> getWeather(viewEvent.id)
            HomeViewEvent.ClearPreviouslyRemember -> clearPreviouslyIfNeeded()
        }
    }

    private fun getWeather(id: String) {
        postLoadingState()
        viewModelScope.launch {
            getWeatherUseCase.execute(id).onSuccess {
                mutableViewState.postValue(
                    HomeViewState.Success(it.copy(
                        future = it.future.subList(1, it.future.size -1)
                    ))
                )
            }.onFailure {
                Timber.e(it)
                postErrorState()
            }
        }
    }

    private fun clearPreviouslyIfNeeded(){
        clearDefaultUseCase.execute()
    }

    private fun postLoadingState() {
        mutableViewState.postValue(
            HomeViewState.Loading
        )
    }

    private fun postErrorState() {
        mutableViewState.postValue(
            HomeViewState.Error
        )
    }
}