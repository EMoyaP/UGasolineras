package com.ug.gasolineralowcost.background

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

class PriceRefreshWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val json = downloadFuelDataset()
            if (json.isNotBlank()) {
                val parsed = JSONObject(json)
                if (parsed.has("ListaEESSPrecio")) {
                    val prefs = applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                    prefs.edit()
                        .putString(KEY_CACHED_DATASET, json)
                        .putLong(KEY_UPDATED_AT, System.currentTimeMillis())
                        .apply()
                }
            }
            Result.success()
        } catch (_: Exception) {
            Result.retry()
        } finally {
            BackgroundPriceScheduler.scheduleNext(applicationContext)
        }
    }

    private fun downloadFuelDataset(): String {
        val connection = (URL(API_URL).openConnection() as HttpURLConnection).apply {
            requestMethod = "GET"
            connectTimeout = 20_000
            readTimeout = 20_000
            setRequestProperty("Accept", "application/json")
        }
        return try {
            val code = connection.responseCode
            if (code !in 200..299) throw IllegalStateException("HTTP $code")
            BufferedReader(connection.inputStream.reader()).use { it.readText() }
        } finally {
            connection.disconnect()
        }
    }

    companion object {
        const val PREFS_NAME = "ugasolineras_background_cache"
        const val KEY_CACHED_DATASET = "cached_fuel_dataset"
        const val KEY_UPDATED_AT = "cached_fuel_dataset_updated_at"
        private const val API_URL =
            "https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/EstacionesTerrestres/"
    }
}
