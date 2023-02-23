package com.example.weatherapp.ui.selector

import com.example.weatherapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectorViewModel @Inject constructor(

) : BaseViewModel<SelectorViewEvent, SelectorViewState>() {
    override fun onViewEvent(viewEvent: SelectorViewEvent) {
        TODO("Not yet implemented")
    }
}