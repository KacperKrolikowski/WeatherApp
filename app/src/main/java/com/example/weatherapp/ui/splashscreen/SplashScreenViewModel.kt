package com.example.weatherapp.ui.splashscreen

import com.example.domain.usecases.CheckSavedUseCase
import com.example.weatherapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val checkSavedUseCase: CheckSavedUseCase
) : BaseViewModel<SplashScreenViewEvent, SplashScreenViewState>() {
    override fun onViewEvent(viewEvent: SplashScreenViewEvent) {
        when (viewEvent) {
            SplashScreenViewEvent.GetData -> checkSavedItem()
        }
    }

    private fun checkSavedItem() {
        mutableViewState.postValue(
            SplashScreenViewState.Success(
                checkSavedUseCase.execute()
            )
        )
    }
}