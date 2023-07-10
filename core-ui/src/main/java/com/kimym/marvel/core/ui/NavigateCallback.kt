package com.kimym.marvel.core.ui

import android.view.View

fun interface NavigateCallback {
    fun navigate(view: View, id: Int?)
}
