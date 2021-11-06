package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.drawable.ColorDrawable
import android.view.View
import kotlin.math.ceil

fun reflectIndexHorizontally(index: Int) =
    ((((((ceil((index / spanCount).toDouble())) + spanCount) - (ceil((index / spanCount).toDouble()))) - (ceil((index / spanCount).toDouble()))) + (spanCount - 1) * (((ceil((index / spanCount).toDouble())) + spanCount) - (ceil((index / spanCount).toDouble())) - (ceil((index / spanCount).toDouble())))) + index.mod(spanCount) - spanCount).toInt()

fun reflectIndexVertically(index: Int) =
    (index - (index.mod(spanCount))) + (spanCount - (index.mod(spanCount))) - 1

fun CanvasActivity.extendedOnPixelTapped(pixel: View) {
    if (isHorizontalMirrorEnabled && !colorPickerMode) {
        pixel.setBackgroundColor(getSelectedColour())
        data[reflectIndexHorizontally(data.indexOf(pixel))].setBackgroundColor(getSelectedColour())
    } else if (isVerticalMirrorEnabled && !colorPickerMode) {
        pixel.setBackgroundColor(getSelectedColour())
        data[reflectIndexVertically(data.indexOf(pixel))].setBackgroundColor(getSelectedColour())
    } else if (colorPickerMode) {
        (pixel.background)?.let { setPixelColour((it as ColorDrawable).color) }
    } else if (wantsToChangeBackground) {
        if (!hasSetBackgroundYet) {
            hasSetBackgroundYet = true
            data.forEach {
                if (it.background == null) it.setBackgroundColor(getSelectedColour())
                currentBackground = getSelectedColour()
            }
        } else {
            data.forEach {
                if ((it.background as ColorDrawable).color == currentBackground) it.setBackgroundColor(
                    getSelectedColour()
                )
            }
            currentBackground = getSelectedColour()
        }
    } else if (isErasing) {
        if (!hasSetBackgroundYet) pixel.background = null else pixel.setBackgroundColor(
            currentBackground!!
        )
    } else {
        pixel.setBackgroundColor(getSelectedColour())
    }
}