package com.krolikowski.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.krolikowski.weatherapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        bottom_nav.setupWithNavController(nav_host_fragment.findNavController())
        nav_host_fragment.findNavController()
            .addOnDestinationChangedListener { controller, destination, arguments ->
                when(destination.id){
                    R.id.WeatherFragment, R.id.WeekWeatherFragment, R.id.SettingsFragment ->{
                        bottom_nav.visibility = View.VISIBLE
                    }
                }
            }
    }
}