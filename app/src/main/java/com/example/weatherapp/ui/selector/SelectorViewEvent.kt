package com.example.weatherapp.ui.selector

import com.example.domain.entities.SearchEntity
import com.example.weatherapp.base.BaseViewEvent

sealed class SelectorViewEvent : BaseViewEvent {
    data class SearchByQuery(val query: String): SelectorViewEvent()
    data class SavePlace(val searchEntity: SearchEntity, val setAsDefault: Boolean) : SelectorViewEvent()
}