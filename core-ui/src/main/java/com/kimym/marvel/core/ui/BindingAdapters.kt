package com.kimym.marvel.core.ui

import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import com.bumptech.glide.Glide

@BindingAdapter("setImageWithUrl")
fun ImageView.setImageWithUrl(url: String?) {
    url?.let {
        Glide.with(context)
            .load(url)
            .into(this)
    }
}

@BindingAdapter("navigate")
fun Toolbar.setSettingMenuClickListener(url: String) {
    setOnMenuItemClickListener { menu ->
        when (menu.itemId) {
            R.id.menu_setting -> {
                val request = NavDeepLinkRequest.Builder
                    .fromUri(url.toUri())
                    .build()
                findNavController().navigate(request)
            }
        }
        true
    }
}
