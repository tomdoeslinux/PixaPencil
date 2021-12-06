package com.realtomjoney.pyxlmoose.activities.canvas

import android.view.View

fun CanvasActivity.extendedInitPixels(): List<View> {
    val list = mutableListOf<View>()
    for (i in 1..spanCount * spanCount) {
        list.add(View(this))
    }
    data = list
    return list.toList()
}