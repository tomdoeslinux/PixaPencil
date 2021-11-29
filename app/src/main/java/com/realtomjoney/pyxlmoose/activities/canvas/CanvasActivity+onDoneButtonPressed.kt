package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.drawable.ColorDrawable
import com.realtomjoney.pyxlmoose.extensions.navigateHome

fun CanvasActivity.extendedOnDoneButtonPressed(selectedColor: Int) {
    setPixelColour(selectedColor)
    navigateHome(supportFragmentManager, colorPickerFragmentInstance, binding.rootLayout, binding.colorPickerFragmentHost,"PyxlMoose")
}

fun CanvasActivity.extendedOnDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) {
    data.forEach { if (it.background != null && (it.background as ColorDrawable).color == colorToFind && colorToReplace != null) it.setBackgroundColor(colorToReplace) }
    canvasFragmentInstance.modifyPixels(data)
    navigateHome(supportFragmentManager, colorPickerFragmentInstance, binding.rootLayout, binding.colorPickerFragmentHost,"PyxlMoose")
}