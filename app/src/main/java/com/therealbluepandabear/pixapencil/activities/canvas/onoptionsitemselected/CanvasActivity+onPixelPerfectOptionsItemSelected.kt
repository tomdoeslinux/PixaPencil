package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.menu
import com.therealbluepandabear.pixapencil.activities.canvas.sharedPreferenceObject
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun onPixelPerfectOptionsItemSelected() {
    pixelGridViewInstance.pixelPerfectMode = !pixelGridViewInstance.pixelPerfectMode

    menu.findItem(R.id.activityCanvasTopAppMenu_pixel_perfect_item).isChecked = pixelGridViewInstance.pixelPerfectMode

    with (sharedPreferenceObject.edit()) {
        putBoolean(StringConstants.Identifiers.SharedPreferencePixelPerfectIdentifier, pixelGridViewInstance.pixelPerfectMode)
        apply()
    }
}