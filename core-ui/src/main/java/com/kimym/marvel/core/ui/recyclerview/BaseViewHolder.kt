package com.kimym.marvel.core.ui.recyclerview

import androidx.core.net.toUri
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kimym.marvel.core.ui.NavigateCallback
import com.kimym.marvel.core.ui.R

abstract class BaseViewHolder<B : ViewDataBinding>(
    protected val binding: B
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        private val navOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.enter_from_right)
            .setExitAnim(R.anim.exit_to_left)
            .setPopEnterAnim(R.anim.enter_from_left)
            .setPopExitAnim(R.anim.exit_to_right)
            .build()

        val callback = NavigateCallback { view, id ->
            id?.let {
                val request = NavDeepLinkRequest.Builder
                    .fromUri("marvel://detail?id=$id".toUri())
                    .build()
                view.findNavController().navigate(request = request, navOptions = navOptions)
            }
        }
    }
}
