package com.kimym.marvel.core.ui.jankstats

import android.view.Window
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.metrics.performance.JankStats
import timber.log.Timber

class JankStatsLifecycleEventObserver(window: Window) : LifecycleEventObserver {
    private val jankStats by lazy {
        JankStats.createAndTrack(window) { frameData ->
            frameData.takeIf { frameData.isJank }?.let { Timber.d("$frameData") }
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_RESUME) {
            jankStats.isTrackingEnabled = true
        } else if (event == Lifecycle.Event.ON_PAUSE) {
            jankStats.isTrackingEnabled = false
        }
    }
}
