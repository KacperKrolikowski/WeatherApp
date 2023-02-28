package com.example.weatherapp.ui.selector

import com.example.weatherapp.base.BaseViewEvent

sealed class SelectorViewEvent : BaseViewEvent {
    data class SearchByQuery(val query: String): SelectorViewEvent()
}