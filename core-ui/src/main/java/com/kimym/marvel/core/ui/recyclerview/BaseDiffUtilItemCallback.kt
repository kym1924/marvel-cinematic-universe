package com.kimym.marvel.core.ui.recyclerview

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtilItemCallback<T : Any>(
    private val areItemsSame: (oldItem: T, newItem: T) -> Boolean,
    private val areContentsSame: (oldItem: T, newItem: T) -> Boolean
) : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return areItemsSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return areContentsSame(oldItem, newItem)
    }
}
