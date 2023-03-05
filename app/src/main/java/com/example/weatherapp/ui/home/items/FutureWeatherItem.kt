package com.example.weatherapp.ui.home.items

import android.annotation.SuppressLint
import android.view.View
import com.example.domain.entities.FutureWeatherEntity
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemFutureWeatherBinding
import com.example.weatherapp.utils.clear
import com.example.weatherapp.utils.loadFromUrl
import com.xwray.groupie.viewbinding.BindableItem
import com.xwray.groupie.viewbinding.GroupieViewHolder
import java.text.SimpleDateFormat
import java.util.*

class FutureWeatherItem(
    private val item: FutureWeatherEntity
) : BindableItem<ItemFutureWeatherBinding>() {
    override fun getLayout() = R.layout.item_future_weather

    override fun initializeViewBinding(view: View) = ItemFutureWeatherBinding.bind(view)

    override fun bind(binding: ItemFutureWeatherBinding, position: Int) {
        with(binding) {
            dateText.text = formatDate(item.date)
            weatherIcon.loadFromUrl(item.conditionImageUrl)
            avgTemperatureText.text = item.avgTemperature.toString()
            minTemperatureText.text = item.minTemperature.toString()
            maxTemperatureText.text = item.maxTemperature.toString()
        }
    }

    override fun unbind(viewHolder: GroupieViewHolder<ItemFutureWeatherBinding>) {
        viewHolder.binding.weatherIcon.clear()
        super.unbind(viewHolder)
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(date: String): String {
        val inputDate = SimpleDateFormat("yyyy-MM-dd").parse(date)
        return inputDate?.let {
            val calendar = Calendar.getInstance()
            calendar.time = inputDate
            "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH)}"
        } ?: ""
    }
}