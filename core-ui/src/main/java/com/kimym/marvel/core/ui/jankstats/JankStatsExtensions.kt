package com.kimym.marvel.core.ui.jankstats

import androidx.databinding.ViewDataBinding
import androidx.metrics.performance.PerformanceMetricsState
import androidx.recyclerview.widget.RecyclerView

const val CURRENT_DESTINATION = "CurrentDestination"
private const val RECYCLER_VIEW = "RecyclerView"
private const val DRAGGING = "Dragging"
private const val SETTLING = "Settling"

fun ViewDataBinding.getMetricsStateHolder(): PerformanceMetricsState.Holder {
    return PerformanceMetricsState.getHolderForHierarchy(root)
}

fun PerformanceMetricsState.Holder.putState(
    key: String,
    value: String
) {
    state?.putState(key, value)
}

fun RecyclerView.addOnScrollListenerForJankStats(holder: PerformanceMetricsState.Holder) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            when (newState) {
                RecyclerView.SCROLL_STATE_DRAGGING -> holder.putState(RECYCLER_VIEW, DRAGGING)
                RecyclerView.SCROLL_STATE_SETTLING -> holder.putState(RECYCLER_VIEW, SETTLING)
                else -> holder.state?.removeState(RECYCLER_VIEW)
            }
        }
    })
}
