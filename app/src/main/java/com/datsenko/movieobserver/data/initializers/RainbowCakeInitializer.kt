package com.datsenko.movieobserver.data.initializers

import android.content.Context
import androidx.startup.Initializer
import co.zsmb.rainbowcake.config.Loggers
import co.zsmb.rainbowcake.config.rainbowCake
import co.zsmb.rainbowcake.timber.TIMBER
import com.datsenko.movieobserver.AndroidApp
import com.datsenko.movieobserver.BuildConfig

class RainbowCakeInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        (context.applicationContext as AndroidApp).rainbowCake {
            isDebug = BuildConfig.DEBUG
            consumeExecuteExceptions = false
            logger = Loggers.TIMBER
        }
    }

    override fun dependencies() = listOf<Class<out Initializer<*>>>()
}
