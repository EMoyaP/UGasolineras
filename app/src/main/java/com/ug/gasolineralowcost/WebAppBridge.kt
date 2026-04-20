package com.ug.gasolineralowcost

import android.content.Context
import android.webkit.JavascriptInterface
import com.ug.gasolineralowcost.background.PriceRefreshWorker

class WebAppBridge(context: Context) {

    private val prefs = context.applicationContext.getSharedPreferences(
        PriceRefreshWorker.PREFS_NAME,
        Context.MODE_PRIVATE
    )
    private val uiCachePrefs = context.applicationContext.getSharedPreferences(
        "ugasolineras_ui_cache",
        Context.MODE_PRIVATE
    )

    @JavascriptInterface
    fun getCachedFuelDataset(): String {
        return prefs.getString(PriceRefreshWorker.KEY_CACHED_DATASET, "") ?: ""
    }

    @JavascriptInterface
    fun getCachedFuelDatasetUpdatedAt(): Long {
        return prefs.getLong(PriceRefreshWorker.KEY_UPDATED_AT, 0L)
    }

    @JavascriptInterface
    fun getCachedStaticArea(): String {
        return uiCachePrefs.getString(KEY_STATIC_AREA_CACHE, "") ?: ""
    }

    @JavascriptInterface
    fun saveCachedStaticArea(payload: String?) {
        uiCachePrefs.edit()
            .putString(KEY_STATIC_AREA_CACHE, payload ?: "")
            .apply()
    }

    @JavascriptInterface
    fun getCachedVolatilePrices(): String {
        return uiCachePrefs.getString(KEY_VOLATILE_CACHE, "") ?: ""
    }

    @JavascriptInterface
    fun saveCachedVolatilePrices(payload: String?) {
        uiCachePrefs.edit()
            .putString(KEY_VOLATILE_CACHE, payload ?: "")
            .apply()
    }

    companion object {
        private const val KEY_STATIC_AREA_CACHE = "cached_static_area_json"
        private const val KEY_VOLATILE_CACHE = "cached_volatile_prices_json"
    }
}
