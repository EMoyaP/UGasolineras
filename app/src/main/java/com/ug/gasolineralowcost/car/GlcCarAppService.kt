package com.ug.gasolineralowcost.car

import android.content.pm.ApplicationInfo
import androidx.car.app.CarAppService
import androidx.car.app.Session
import androidx.car.app.validation.HostValidator
import com.ug.gasolineralowcost.R

class GlcCarAppService : CarAppService() {
    override fun createHostValidator(): HostValidator {
        val isDebuggable =
            (applicationContext.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0
        if (isDebuggable) {
            return HostValidator.ALLOW_ALL_HOSTS_VALIDATOR
        }
        return HostValidator.Builder(applicationContext)
            .addAllowedHosts(R.array.hosts_allowlist)
            .build()
    }

    override fun onCreateSession(): Session = GlcCarSession()
}
