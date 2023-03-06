package com.example.weatherapp.ui.selector

import androidx.lifecycle.viewModelScope
import com.example.domain.entities.SearchEntity
import com.example.domain.usecases.DeleteSavedPlaceUseCase
import com.example.domain.usecases.GetSavedPlacesUseCase
import com.example.domain.usecases.SavePlaceUseCase
import com.example.domain.usecases.SearchByQueryUseCase
import com.example.weatherapp.base.BaseViewModel
import com.example.weatherapp.ui.selector.SelectorFragment.Companion.MINIMAL_QUERY_LENGTH
import com.example.weatherapp.ui.selector.items.SearchItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SelectorViewModel @Inject constructor(
    private val searchByQueryUseCase: SearchByQueryUseCase,
    private val getSavedPlacesUseCase: GetSavedPlacesUseCase,
    private val savePlaceUseCase: SavePlaceUseCase,
    private val deleteSavedPlaceUseCase: DeleteSavedPlaceUseCase
) : BaseViewModel<SelectorViewEvent, SelectorViewState>() {

    private var searchJob: Job? = null
    override fun onViewEvent(viewEvent: SelectorViewEvent) {
        when (viewEvent) {
            is SelectorViewEvent.SearchByQuery -> onSearchQueryChanged(viewEvent.query)
            is SelectorViewEvent.SavePlace -> savePlace(viewEvent.searchEntity)
        }
    }

    private fun onSearchQueryChanged(query: String) {
        if (query.length >= MINIMAL_QUERY_LENGTH) {
            postLoadingState()
            searchDebounced(query)
        } else {
            mutableViewState.postValue(
                SelectorViewState.Success(listOf())
            )
        }
    }

    private fun searchDebounced(searchText: String) {
        searchJob?.cancel()
        searchJob = searchText.takeIf { it.isNotEmpty() }?.let { text ->
            viewModelScope.launch {
                delay(DEBOUNCE_TIME)
                searchForResults(text)
            }
        }
    }

    private fun deleteFromHistory(searchEntity: SearchEntity) {
        viewModelScope.launch {
            deleteSavedPlaceUseCase.execute(searchEntity)
        }
    }

    private fun searchForResults(query: String) {
        viewModelScope.launch {
            getSavedPlacesUseCase.execute().collectLatest { list ->
                val databaseResults =
                    list.filter { it.name.contains(query) }.take(DEFAULT_SIZE).map {
                        SearchItem(it, ::deleteFromHistory)
                    }
                searchByQueryUseCase.execute(query).let { result ->
                    result.onSuccess { sourceItems ->
                        val results = sourceItems.map { searchEntity ->
                            SearchItem(
                                searchEntity,
                                ::deleteFromHistory
                            )
                        }

                        mutableViewState.postValue(
                            SelectorViewState.Success(
                                databaseResults + results
                            )
                        )
                    }.onFailure {
                        Timber.e(it)
                        postErrorState()
                    }
                }
            }
        }
    }

    private fun savePlace(searchEntity: SearchEntity) {
        viewModelScope.launch {
            savePlaceUseCase.execute(searchEntity)
        }
    }

    private fun postLoadingState() {
        mutableViewState.postValue(
            SelectorViewState.Loading
        )
    }

    private fun postErrorState() {
        mutableViewState.postValue(
            SelectorViewState.Error
        )
    }

    companion object {
        private const val DEBOUNCE_TIME = 600L
        private const val DEFAULT_SIZE = 3

    }
}