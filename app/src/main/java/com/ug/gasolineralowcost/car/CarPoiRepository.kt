package com.ug.gasolineralowcost.car

data class CarPoi(
    val id: String,
    val name: String,
    val address: String,
    val subtitle: String,
    val distanceKm: Double,
    val lat: Double,
    val lng: Double,
    val type: PoiType
)

enum class PoiType { FUEL, EV }

object CarPoiRepository {
    private val sampleData = listOf(
        CarPoi("fuel_1", "Repsol Centro", "Av. de la Estación, Elche", "Gasolinera · 1.52 €/L", 2.4, 38.2699, -0.6980, PoiType.FUEL),
        CarPoi("fuel_2", "Ballenoil Norte", "Ronda Vall d'Uixó, Elche", "Gasolinera · 1.47 €/L", 4.1, 38.2781, -0.7021, PoiType.FUEL),
        CarPoi("fuel_3", "Cepsa Universidad", "Av. de la Universidad, Elche", "Gasolinera · 1.49 €/L", 3.2, 38.2665, -0.6889, PoiType.FUEL),
        CarPoi("ev_1", "Iberdrola Fast Charger", "Centro Comercial L'Aljub", "Recarga · 120 kW", 3.8, 38.2744, -0.7039, PoiType.EV),
        CarPoi("ev_2", "Zunder HPC Elche", "Avinguda de Crevillent", "Recarga · 180 kW", 5.6, 38.2520, -0.7022, PoiType.EV),
        CarPoi("ev_3", "Endesa X Alicante Sur", "Av. de Elche, Alicante", "Recarga · 50 kW", 18.9, 38.3365, -0.5040, PoiType.EV)
    )

    fun all(): List<CarPoi> = sampleData.sortedBy { it.distanceKm }

    fun search(query: String): List<CarPoi> {
        val q = query.trim().lowercase()
        if (q.isEmpty()) return all()
        return all().filter { poi ->
            poi.name.lowercase().contains(q) ||
                poi.address.lowercase().contains(q) ||
                poi.subtitle.lowercase().contains(q) ||
                poi.type.name.lowercase().contains(q)
        }
    }
}
