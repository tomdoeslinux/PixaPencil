package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.drawable.ColorDrawable
import android.view.View
import com.realtomjoney.pyxlmoose.utility.MathExtensions

fun CanvasActivity.extendedOnPixelTapped(pixel: View) {
    if (currentTool == Tools.HORIZONTAL_MIRROR_TOOL) {
        pixel.setBackgroundColor(getSelectedColor())
        data[MathExtensions.reflectIndexHorizontally(data.indexOf(pixel), spanCount)].setBackgroundColor(getSelectedColor())
    } else if (currentTool == Tools.VERTICAL_MIRROR_TOOL) {
        pixel.setBackgroundColor(getSelectedColor())
        data[MathExtensions.reflectIndexVertically(data.indexOf(pixel), spanCount)].setBackgroundColor(getSelectedColor())
    } else if (currentTool == Tools.COLOR_PICKER_TOOL) {
        (pixel.background)?.let { setPixelColor((it as ColorDrawable).color) }
    } else if (currentTool == Tools.CHANGE_BACKGROUND_TOOL) {
        if (!hasSetBackgroundYet) {
            hasSetBackgroundYet = true
            data.forEach {
                if (it.background == null) it.setBackgroundColor(getSelectedColor())
                currentBackground = getSelectedColor()
            }
        } else {
            data.forEach {
                if ((it.background as ColorDrawable).color == currentBackground) it.setBackgroundColor(
                    getSelectedColor()
                )
            }
            currentBackground = getSelectedColor()
        }
    } else if (currentTool == Tools.ERASE_TOOL) {
        if (!hasSetBackgroundYet) pixel.background = null else pixel.setBackgroundColor(
            currentBackground!!
        )
    } else {
        pixel.setBackgroundColor(getSelectedColor())
    }
}