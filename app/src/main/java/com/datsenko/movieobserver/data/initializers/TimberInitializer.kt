package com.datsenko.movieobserver.data.initializers

import android.content.Context
import androidx.startup.Initializer
import com.datsenko.movieobserver.BuildConfig
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun dependencies() = listOf<Class<out Initializer<*>>>()
}
