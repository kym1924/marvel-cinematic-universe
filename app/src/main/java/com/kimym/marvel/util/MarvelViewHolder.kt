package com.kimym.marvel.util

import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kimym.marvel.BR
import com.kimym.marvel.NavGraphDirections

class MarvelViewHolder<T>(
    private val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(model: T) {
        with(binding) {
            setVariable(BR.model, model)
            setVariable(BR.callback, callback)
            executePendingBindings()
        }
    }

    companion object {
        val callback = NavigateCallback { view, title ->
            title?.let {
                val action = NavGraphDirections.actionGlobalDetailFragment(title)
                view.findNavController().navigate(action)
            }
        }
    }
}
