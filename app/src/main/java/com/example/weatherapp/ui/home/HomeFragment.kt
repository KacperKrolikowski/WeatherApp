package com.example.weatherapp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat.getColor
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.entities.WeatherData
import com.example.weatherapp.R
import com.example.weatherapp.base.BaseFragment
import com.example.weatherapp.databinding.FragmentHomeBinding
import com.example.weatherapp.ui.home.items.FutureWeatherItem
import com.example.weatherapp.utils.loadFromUrl
import com.google.android.material.snackbar.Snackbar
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewState, HomeViewEvent, HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    override val viewModel: HomeViewModel by viewModels()

    private val args: HomeFragmentArgs by navArgs()
    private val futureWeatherAdapter = GroupieAdapter()

    override fun handleViewState(viewState: HomeViewState) {
        when (viewState) {
            is HomeViewState.Success -> setSuccessState(viewState.results)
            HomeViewState.Loading -> setLoadingState()
            HomeViewState.Error -> setErrorState()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewEvent(HomeViewEvent.GetWeatherData(args.locationId))
        with(binding) {
            recyclerView.adapter = futureWeatherAdapter
            backButton.setOnClickListener {
                viewModel.onViewEvent(HomeViewEvent.ClearPreviouslyRemember)
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToSelectorFragment()
                )
            }
        }
    }

    override fun onDestroyView() {
        binding.recyclerView.adapter = null
        super.onDestroyView()
    }

    @SuppressLint("SetTextI18n")
    private fun setSuccessState(weatherData: WeatherData) {
        with(binding) {
            with(weatherData.current) {
                temperatureValueText.apply {
                    text = "$temperature \u2103"
                    setTextColor(
                        when {
                            temperature < LOW_TEMPERATURE -> getColor(context, R.color.temperature_text_low)
                            temperature < MEDIUM_TEMPERATURE -> getColor(context, R.color.temperature_text_medium)
                            else -> getColor(context, R.color.temperature_text_high)
                        }
                    )
                }
                windValueText.text = "$windDirection $windSpeed km/h"
                pressureValueText.text = "$pressure hPa"
                feelsLikeValueText.text = "$feelsLikeTemperature \u2103"
                locationText.text = location
                weatherImage.loadFromUrl(weatherIconUrl)
            }
        }
        binding.recyclerView.isVisible = true
        futureWeatherAdapter.update(weatherData.future.map { FutureWeatherItem(it) })
        binding.progressIndicator.isVisible = false
    }

    private fun setLoadingState() {
        binding.progressIndicator.isVisible = true
    }

    private fun setErrorState() {
        Snackbar.make(
            this.requireView(),
            getString(R.string.something_went_wrong_info),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    companion object{
        private const val LOW_TEMPERATURE = 10
        private const val MEDIUM_TEMPERATURE = 20
    }
}