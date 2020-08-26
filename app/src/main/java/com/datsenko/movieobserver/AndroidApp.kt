package com.datsenko.movieobserver

import android.app.Application
import android.os.StrictMode
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AndroidApp : Application() {

    override fun onCreate() {
        enableStrictMode()
        super.onCreate()
    }

    private fun enableStrictMode() {
        // TODO: 8/24/20 move this to App StartUp once it becomes stable and LeakCanary will support it.
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyDeath()
                    .build()
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
        }
    }
}
