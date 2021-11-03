package com.realtomjoney.pyxlmoose.activities.canvas

import android.view.View
import androidx.fragment.app.FragmentTransaction

fun CanvasActivity.extendedOnDoneButtonPressed(selectedColor: Int) {
    with(supportFragmentManager.beginTransaction()) {
        remove(colorPickerFragmentInstance)
        commit()
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
    }
    setPixelColour(selectedColor)

    with (binding) {
        colorPickerFragmentHost.visibility = View.INVISIBLE
        doneButton.scaleX = 1f
        doneButton.scaleY = 1f
        doneButton.visibility = View.VISIBLE
    }

    title = "PyxlMoose"
}