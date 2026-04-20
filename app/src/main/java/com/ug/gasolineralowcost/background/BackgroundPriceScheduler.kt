package com.ug.gasolineralowcost.background

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.Calendar
import java.util.concurrent.TimeUnit

object BackgroundPriceScheduler {

    const val UNIQUE_WORK_NAME = "ugasolineras_hourly_price_refresh"

    fun scheduleNext(context: Context) {
        val request = OneTimeWorkRequestBuilder<PriceRefreshWorker>()
            .setInitialDelay(millisUntilNextHour(), TimeUnit.MILLISECONDS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()

        WorkManager.getInstance(context.applicationContext)
            .enqueueUniqueWork(UNIQUE_WORK_NAME, ExistingWorkPolicy.REPLACE, request)
    }

    private fun millisUntilNextHour(): Long {
        val now = Calendar.getInstance()
        val next = now.clone() as Calendar
        next.add(Calendar.HOUR_OF_DAY, 1)
        next.set(Calendar.MINUTE, 0)
        next.set(Calendar.SECOND, 0)
        next.set(Calendar.MILLISECOND, 0)
        return (next.timeInMillis - now.timeInMillis).coerceAtLeast(60_000L)
    }
}
