package com.ug.gasolineralowcost.car

import android.content.Intent
import android.net.Uri
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.ActionStrip
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template

class StationDetailScreen(
    carContext: CarContext,
    private val station: CarPoi
) : Screen(carContext) {

    override fun onGetTemplate(): Template {
        val pane = Pane.Builder()
            .addRow(Row.Builder().setTitle(station.name).addText(station.address).build())
            .addRow(Row.Builder().setTitle("Información").addText(station.subtitle).build())
            .addRow(Row.Builder().setTitle("Distancia").addText("${station.distanceKm} km").build())
            .build()

        return PaneTemplate.Builder(pane)
            .setTitle("Detalle")
            .setHeaderAction(Action.BACK)
            .setActionStrip(
                ActionStrip.Builder()
                    .addAction(
                        Action.Builder()
                            .setTitle("Navegar")
                            .setOnClickListener {
                                val geoUri = Uri.parse("geo:${station.lat},${station.lng}?q=${Uri.encode(station.name)}")
                                val navigateIntent = Intent(CarContext.ACTION_NAVIGATE, geoUri)
                                carContext.startCarApp(navigateIntent)
                            }
                            .build()
                    )
                    .build()
            )
            .build()
    }
}
