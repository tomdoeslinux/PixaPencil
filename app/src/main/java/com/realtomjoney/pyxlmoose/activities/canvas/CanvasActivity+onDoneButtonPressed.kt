package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.drawable.ColorDrawable
import com.realtomjoney.pyxlmoose.extensions.navigateHome

fun CanvasActivity.extendedOnDoneButtonPressed(selectedColor: Int) {
    currentFragmentInstance = null
    setPixelColour(selectedColor)
    navigateHome(supportFragmentManager, colorPickerFragmentInstance, binding.rootLayout, binding.colorPickerFragmentHost,"PyxlMoose")
}

fun CanvasActivity.extendedOnDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) {
    currentFragmentInstance = null
    data.forEach { if (it.background != null && (it.background as ColorDrawable).color == colorToFind && colorToReplace != null) it.setBackgroundColor(colorToReplace) }
    canvasFragmentInstance.modifyPixels(data)
    navigateHome(supportFragmentManager, findAndReplaceFragmentInstance, binding.rootLayout, binding.colorPickerFragmentHost,"PyxlMoose")
}