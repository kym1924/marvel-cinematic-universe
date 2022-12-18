package com.kimym.marvel.util

import android.view.View

fun interface NavigateCallback {
    fun navigate(view: View, title: String?)
}
