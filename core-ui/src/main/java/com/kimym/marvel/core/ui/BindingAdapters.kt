package com.kimym.marvel.core.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setImageWithUrl")
fun ImageView.setImageWithUrl(url: String?) {
    url?.let {
        Glide.with(context)
            .load(url)
            .into(this)
    }
}
