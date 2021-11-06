package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.fragment.app.FragmentTransaction

fun CanvasActivity.onNavigatedBack() {
    with (binding) {
        colorPickerFragmentHost.visibility = View.INVISIBLE
        doneButton.scaleX = 1f
        doneButton.scaleY = 1f
        doneButton.visibility = View.VISIBLE
    }

    title = "PyxlMoose"
}

fun CanvasActivity.extendedOnDoneButtonPressed(selectedColor: Int) {
    with(supportFragmentManager.beginTransaction()) {
        remove(colorPickerFragmentInstance)
        commit()
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
    }
    setPixelColour(selectedColor)
    onNavigatedBack()
}

fun CanvasActivity.extendedOnDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) {
    with(supportFragmentManager.beginTransaction()) {
        remove(findAndReplaceFragmentInstance)
        commit()
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
    }

    data.forEach {
        if (it.background != null) {
            if ((it.background as ColorDrawable).color == colorToFind) {
                if (colorToReplace != null) {
                    it.setBackgroundColor(colorToReplace)
                }
            }
        }
    }

    canvasFragmentInstance.modifyPixels(data)
    onNavigatedBack()
}