package com.realtomjoney.pyxlmoose.activity.canvas

import android.graphics.drawable.ColorDrawable
import android.view.View

fun CanvasActivity.extendedOnPixelTapped(pixel: View) {
    if (isMirrorMode && !colorPickerMode) {
        pixel.setBackgroundColor(getSelectedColour())
        data[((data.indexOf(pixel)) - ((data.indexOf(pixel)).mod(spanCount))) + (spanCount -  ((data.indexOf(pixel)).mod(spanCount))) - 1].setBackgroundColor(getSelectedColour()) // Credits to PapaBread for this masterpiece of a solution
    } else if (colorPickerMode) {
        (pixel.background)?.let { setPixelColour((it as ColorDrawable).color) }
    } else if (wantsToChangeBackground) {
        if (!hasSetBackgroundYet) {
            hasSetBackgroundYet = true
            data.forEach {
                if (it.background == null) {
                    it.setBackgroundColor(getSelectedColour())
                }

                currentBackground = getSelectedColour()
            }
        } else {
            data.forEach {
            if ((it.background as ColorDrawable).color == currentBackground) {
                    it.setBackgroundColor(getSelectedColour())
                }
            }

            currentBackground = getSelectedColour()
        }
    }
    else { pixel.setBackgroundColor(getSelectedColour()) }
}