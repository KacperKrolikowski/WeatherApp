package com.example.weatherapp.ui.home

import androidx.fragment.app.viewModels
import com.example.weatherapp.base.BaseFragment
import com.example.weatherapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewState, HomeViewEvent, HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    override val viewModel: HomeViewModel by viewModels()

    override fun handleViewState(viewState: HomeViewState) {
    }
}