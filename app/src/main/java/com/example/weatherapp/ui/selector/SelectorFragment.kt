package com.example.weatherapp.ui.selector

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.base.BaseFragment
import com.example.weatherapp.databinding.FragmentSelectorBinding
import com.example.weatherapp.ui.selector.items.SearchItem
import com.google.android.material.snackbar.Snackbar
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectorFragment :
    BaseFragment<FragmentSelectorBinding, SelectorViewState, SelectorViewEvent, SelectorViewModel>(
        FragmentSelectorBinding::inflate
    ) {
    override val viewModel: SelectorViewModel by viewModels()

    private var currentQuery: String = ""
    private val searchTextWatcher = SearchTextWatcher()

    private val contentAdapter = GroupieAdapter()

    override fun handleViewState(viewState: SelectorViewState) {
        when (viewState) {
            is SelectorViewState.Success -> setSearchResults(viewState.results)
            SelectorViewState.Error -> setErrorState()
            SelectorViewState.Loading -> setLoadingState()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            searchText.addTextChangedListener(searchTextWatcher)
            citiesRecyclerView.adapter = contentAdapter
        }
    }

    override fun onDestroyView() {
        with(binding) {
            citiesRecyclerView.adapter = null
            searchText.removeTextChangedListener(searchTextWatcher)
        }
        super.onDestroyView()
    }

    private fun setSearchResults(results: List<SearchItem>) {
        binding.progressIndicator.isVisible = false
        binding.selectFromListText.isVisible = true
        contentAdapter.apply {
            update(results)
            setOnItemClickListener { item, _ ->
                if (item is SearchItem) {
                    viewModel.onViewEvent(SelectorViewEvent.SavePlace(item.item))
                    findNavController().navigate(
                        SelectorFragmentDirections.actionSelectorFragmentToHomeFragment(
                            item.item.id
                        )
                    )
                }
            }
        }
    }

    private fun setEmptyList() {
        binding.selectFromListText.isVisible = false
        contentAdapter.update(listOf())
    }

    private fun setLoadingState() {
        binding.apply {
            selectFromListText.isVisible = false
            progressIndicator.isVisible = true
        }
    }

    private fun setErrorState() {
        Snackbar.make(
            this.requireView(),
            getString(R.string.something_went_wrong_info),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private inner class SearchTextWatcher : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        override fun afterTextChanged(editable: Editable?) {
            editable?.let { text ->
                when {
                    text.isEmpty() -> {
                        setEmptyList()
                    }
                    text.isNotEmpty() -> {
                        when {
                            (text.length >= MINIMAL_QUERY_LENGTH && text.toString() == currentQuery) -> Unit
                            (text.length >= MINIMAL_QUERY_LENGTH && currentQuery.length >= 2) -> {
                                setLoadingState()
                                setEmptyList()
                                viewModel.onViewEvent(
                                    SelectorViewEvent.SearchByQuery(text.toString())
                                )
                            }

                            (text.length > 1 && currentQuery.isEmpty()) -> {
                                currentQuery = ""
                                binding.searchText.text?.clear()
                                viewModel.onViewEvent(SelectorViewEvent.SearchByQuery(""))
                            }

                            else -> viewModel.onViewEvent(SelectorViewEvent.SearchByQuery(""))
                        }
                    }
                    else -> Unit
                }
                currentQuery = text.toString()
            }
        }
    }

    companion object {
        const val MINIMAL_QUERY_LENGTH = 3
    }
}