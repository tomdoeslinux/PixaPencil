package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.drawable.ColorDrawable
import com.realtomjoney.pyxlmoose.extensions.navigateHome

fun CanvasActivity.extendedOnDoneButtonPressed(selectedColor: Int) {
    currentFragmentInstance = null
    setPixelColour(selectedColor)
    navigateHome(supportFragmentManager, colorPickerFragmentInstance, binding.rootLayout, binding.colorPickerFragmentHost,"PyxlMoose")

    data.forEach { data_it ->
        val index = data.indexOf(data_it)

        if (pixelDataAsViews[index].background != null) data_it.setBackgroundColor((pixelDataAsViews[index].background as ColorDrawable).color)
    }
    canvasFragmentInstance.modifyPixels(data)
}

fun CanvasActivity.extendedOnDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) {
    currentFragmentInstance = null
    data.forEach { if (it.background != null && (it.background as ColorDrawable).color == colorToFind && colorToReplace != null) it.setBackgroundColor(colorToReplace) }
    canvasFragmentInstance.modifyPixels(data)
    navigateHome(supportFragmentManager, findAndReplaceFragmentInstance, binding.rootLayout, binding.colorPickerFragmentHost,"PyxlMoose")
}