package com.therealbluepandabear.pixapencil.activities.canvas

import android.view.View

fun CanvasActivity.updateColorSelectedIndicator(it: View) {
    previousView?.background = background

    previousView = it
    background = it.background
}