package com.therealbluepandabear.pixapencil.extensions

import android.view.Menu

fun Menu.showItems() {
    for (i in 0 until size()) {
        getItem(i).isVisible = true
    }
}