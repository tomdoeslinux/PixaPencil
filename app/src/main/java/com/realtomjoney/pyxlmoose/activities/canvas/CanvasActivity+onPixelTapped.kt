package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.drawable.ColorDrawable
import android.view.View
import com.realtomjoney.pyxlmoose.utility.MathExtensions

fun CanvasActivity.extendedOnPixelTapped(pixel: View) {
    if (isHorizontalMirrorEnabled && !colorPickerMode) {
        pixel.setBackgroundColor(getSelectedColour())
        data[MathExtensions.reflectIndexHorizontally(data.indexOf(pixel), spanCount)].setBackgroundColor(getSelectedColour())
    } else if (isVerticalMirrorEnabled && !colorPickerMode) {
        pixel.setBackgroundColor(getSelectedColour())
        data[MathExtensions.reflectIndexVertically(data.indexOf(pixel), spanCount)].setBackgroundColor(getSelectedColour())
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