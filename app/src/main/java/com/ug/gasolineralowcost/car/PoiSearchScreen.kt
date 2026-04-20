package com.ug.gasolineralowcost.car

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.ItemList
import androidx.car.app.model.Row
import androidx.car.app.model.SearchTemplate
import androidx.car.app.model.SearchTemplate.SearchCallback
import androidx.car.app.model.Template

class PoiSearchScreen(carContext: CarContext) : Screen(carContext), SearchCallback {

    private var query: String = ""

    override fun onSearchTextChanged(searchText: String) {
        query = searchText
        invalidate()
    }

    override fun onSearchSubmitted(searchText: String) {
        query = searchText
        invalidate()
    }

    override fun onGetTemplate(): Template {
        val list = ItemList.Builder()
        CarPoiRepository.search(query).take(30).forEach { poi ->
            list.addItem(
                Row.Builder()
                    .setTitle(poi.name)
                    .addText("${poi.address} - ${poi.subtitle}")
                    .addText("${"%.1f".format(poi.distanceKm)} km")
                    .setOnClickListener { screenManager.push(StationDetailScreen(carContext, poi)) }
                    .build()
            )
        }

        return SearchTemplate.Builder(this)
            .setHeaderAction(Action.BACK)
            .setSearchHint("Buscar gasolinera o recarga")
            .setShowKeyboardByDefault(true)
            .setItemList(list.build())
            .build()
    }
}
