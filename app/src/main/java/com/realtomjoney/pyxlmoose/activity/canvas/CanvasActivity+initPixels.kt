package com.realtomjoney.pyxlmoose.activity.canvas

import android.view.View

fun CanvasActivity.extendedInitPixels(): List<View> {
    return if (index == -1) {
        val list = mutableListOf<View>()
        for (i in 1..spanCount * spanCount) {
            list.add(View(this))
        }
        data = list
        list.toList()
    } else {
        data.toList()
    }
}