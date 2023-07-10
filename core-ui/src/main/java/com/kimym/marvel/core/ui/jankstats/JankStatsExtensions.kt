package com.kimym.marvel.core.ui.jankstats

import androidx.databinding.ViewDataBinding
import androidx.metrics.performance.PerformanceMetricsState
import androidx.recyclerview.widget.RecyclerView

private const val key = "RecyclerView"

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
    addOnScrollListener(
        object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                when (newState) {
                    RecyclerView.SCROLL_STATE_DRAGGING -> holder.putState(key, "Dragging")
                    RecyclerView.SCROLL_STATE_SETTLING -> holder.putState(key, "Settling")
                    else -> holder.state?.removeState(key)
                }
            }
        }
    )
}
