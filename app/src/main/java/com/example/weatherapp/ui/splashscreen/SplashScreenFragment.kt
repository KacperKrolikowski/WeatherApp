package com.example.weatherapp.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.base.BaseFragment
import com.example.weatherapp.databinding.FragmentSplashscreenBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenFragment :
    BaseFragment<FragmentSplashscreenBinding, SplashScreenViewState, SplashScreenViewEvent, SplashScreenViewModel>(
        FragmentSplashscreenBinding::inflate
    ) {
    override val viewModel: SplashScreenViewModel by viewModels()

    override fun handleViewState(viewState: SplashScreenViewState) {
        when (viewState) {
            is SplashScreenViewState.Success -> checkInternetConnection(viewState.id)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onViewEvent(SplashScreenViewEvent.GetData)
    }

    private fun checkInternetConnection(id: String) = when {
        isOnline() && id.isNotEmpty() -> findNavController().navigate(
            SplashScreenFragmentDirections.actionSplashScreenFragmentToHomeFragment(
                id
            )
        )
        isOnline() -> findNavController().navigate(
            SplashScreenFragmentDirections.actionSplashScreenFragmentToSelectorFragment()
        )
        else -> view?.let {
            Snackbar.make(
                it,
                getString(R.string.internet_connection_problem),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun isOnline(): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return capabilities?.let {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || capabilities.hasTransport(
                NetworkCapabilities.TRANSPORT_WIFI
            ) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        } ?: false
    }
}