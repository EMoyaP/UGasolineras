package com.ug.gasolineralowcost.car

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.ActionStrip
import androidx.car.app.model.ItemList
import androidx.car.app.model.ListTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template

class PoiHomeScreen(carContext: CarContext) : Screen(carContext) {

    override fun onGetTemplate(): Template {
        val items = ItemList.Builder()
        CarPoiRepository.all().take(20).forEach { poi ->
            items.addItem(
                Row.Builder()
                    .setTitle(poi.name)
                    .addText("${poi.address} - ${poi.subtitle}")
                    .addText("${"%.1f".format(poi.distanceKm)} km")
                    .setOnClickListener { screenManager.push(StationDetailScreen(carContext, poi)) }
                    .build()
            )
        }

        return ListTemplate.Builder()
            .setTitle("UGasolineras Auto")
            .setSingleList(items.build())
            .setHeaderAction(Action.APP_ICON)
            .setActionStrip(
                ActionStrip.Builder()
                    .addAction(
                        Action.Builder()
                            .setTitle("Buscar")
                            .setOnClickListener { screenManager.push(PoiSearchScreen(carContext)) }
                            .build()
                    )
                    .build()
            )
            .build()
    }
}
