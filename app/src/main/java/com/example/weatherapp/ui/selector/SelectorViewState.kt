package com.example.weatherapp.ui.selector

import com.example.weatherapp.base.BaseViewState
import com.example.weatherapp.ui.selector.items.SearchItem

sealed class SelectorViewState: BaseViewState {
    data class Success(val results: List<SearchItem>): SelectorViewState()
    object Loading : SelectorViewState()
    object Error : SelectorViewState()
}