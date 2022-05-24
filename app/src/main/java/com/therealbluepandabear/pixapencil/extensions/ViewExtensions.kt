package com.therealbluepandabear.pixapencil.extensions

import android.view.View

fun View.setOnLongPressListener(event: (it: View) -> Unit) {
    setOnLongClickListener {
        event(it)
        true
    }
}