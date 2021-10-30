package com.realtomjoney.pyxlmoose.activity.canvas

import android.view.View

fun CanvasActivity.extendedOnPixelTapped(pixel: View) {
    if (isMirrorMode) {
        data[((data.indexOf(pixel)) - ((data.indexOf(pixel)).mod(spanCount))) + (spanCount -  ((data.indexOf(pixel)).mod(spanCount))) - 1].setBackgroundColor(
            getSelectedColour()
        ) // Credits to PapaBread for this masterpiece of a solution
    }
    pixel.setBackgroundColor(getSelectedColour())
}