package com.therealbluepandabear.pixapencil.extensions

import android.view.Menu

fun Menu.hideItems() {
    for (i in 0 until size()) {
        getItem(i).isVisible = false
    }
}

fun Menu.showItems() {
    for (i in 0 until size()) {
        getItem(i).isVisible = true
    }
}