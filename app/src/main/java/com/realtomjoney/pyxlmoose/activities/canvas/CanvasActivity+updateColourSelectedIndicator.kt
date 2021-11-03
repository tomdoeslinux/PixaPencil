package com.realtomjoney.pyxlmoose.activities.canvas

import android.view.View

fun CanvasActivity.extendedUpdateColourSelectedIndicator(it: View) {
    previousView?.background = background

    previousView = it
    background = it.background

    it.background = getGradientDrawable()
}