package com.kimym.marvel.initializer

import android.content.Context
import androidx.startup.Initializer
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        Timber.plant(Timber.DebugTree())
        Timber.d("Timber is Initialized")
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
