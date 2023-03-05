package com.example.weatherapp.ui.selector.items

import android.view.View
import com.example.domain.entities.SearchEntity
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemSearchBinding
import com.xwray.groupie.viewbinding.BindableItem

class SearchItem(
    val item: SearchEntity
) : BindableItem<ItemSearchBinding>() {
    override fun getLayout() = R.layout.item_search

    override fun initializeViewBinding(view: View) = ItemSearchBinding.bind(view)

    override fun bind(binding: ItemSearchBinding, position: Int) {
        with(binding) {
            if (item.isFromHistory.not()) historyIcon.visibility = View.INVISIBLE
            val textBuilder = item.name +
                    ", ${item.region}".takeIf { item.region.isNotEmpty() }.orEmpty() +
                    ", ${item.country}".takeIf { item.country.isNotEmpty() }.orEmpty()
            placeText.text = textBuilder
        }
    }
}