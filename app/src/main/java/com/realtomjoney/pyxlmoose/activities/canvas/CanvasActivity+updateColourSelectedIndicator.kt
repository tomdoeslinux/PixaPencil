package com.realtomjoney.pyxlmoose.activities.canvas

import android.view.View

fun CanvasActivity.extendedUpdateColorSelectedIndicator(it: View) {
    previousView?.background = background

    previousView = it
    background = it.background
}