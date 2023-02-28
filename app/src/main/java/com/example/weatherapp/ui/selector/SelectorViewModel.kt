package com.example.weatherapp.ui.selector

import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.SearchByQueryUseCase
import com.example.weatherapp.base.BaseViewModel
import com.example.weatherapp.ui.selector.SelectorFragment.Companion.MINIMAL_QUERY_LENGTH
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectorViewModel @Inject constructor(
    private val searchByQueryUseCase: SearchByQueryUseCase
) : BaseViewModel<SelectorViewEvent, SelectorViewState>() {

    private var searchJob: Job? = null
    override fun onViewEvent(viewEvent: SelectorViewEvent) {
        when (viewEvent) {
            is SelectorViewEvent.SearchByQuery -> onSearchQueryChanged(viewEvent.query)
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
                searchByQuery(text)
            }
        }
    }

    private fun searchByQuery(query: String) {
        viewModelScope.launch {
            searchByQueryUseCase.execute(query).let { result ->
                result.onSuccess {
                    mutableViewState.postValue(
                        SelectorViewState.Success(it.map { searchEntity ->
                            SearchItem(searchEntity)
                        })
                    )
                }.onFailure {
                    postErrorState()
                }
            }
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
    }
}