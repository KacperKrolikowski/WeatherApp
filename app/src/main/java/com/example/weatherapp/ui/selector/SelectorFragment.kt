package com.example.weatherapp.ui.selector

import androidx.fragment.app.viewModels
import com.example.weatherapp.base.BaseFragment
import com.example.weatherapp.databinding.FragmentSelectorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectorFragment : BaseFragment<FragmentSelectorBinding, SelectorViewState, SelectorViewEvent, SelectorViewModel>(
    FragmentSelectorBinding::inflate
) {
    override val viewModel: SelectorViewModel by viewModels()

    override fun handleViewState(viewState: SelectorViewState) {
        TODO("Not yet implemented")
    }

}