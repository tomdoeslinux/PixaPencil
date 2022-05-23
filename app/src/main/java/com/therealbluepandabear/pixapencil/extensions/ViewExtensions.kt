package com.therealbluepandabear.pixapencil.extensions

import android.view.View

fun View.setOnLongPressListener(event: (it: View) -> Unit) {
    this.setOnLongClickListener {
        event(it)
        true
    }
}