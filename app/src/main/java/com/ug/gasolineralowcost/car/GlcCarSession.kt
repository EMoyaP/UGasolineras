package com.ug.gasolineralowcost.car

import androidx.car.app.Screen
import androidx.car.app.Session

class GlcCarSession : Session() {
    override fun onCreateScreen(intent: android.content.Intent): Screen {
        return PoiHomeScreen(carContext)
    }
}
