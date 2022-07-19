package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.onPixelPerfectOptionsItemSelected() {
    binding.activityCanvasPixelGridView.pixelPerfectMode = !binding.activityCanvasPixelGridView.pixelPerfectMode

    menu.findItem(R.id.activityCanvasTopAppMenu_pixel_perfect_item).isChecked = binding.activityCanvasPixelGridView.pixelPerfectMode

    with (sharedPreferenceObject.edit()) {
        putBoolean(StringConstants.Identifiers.SHARED_PREFERENCE_PIXEL_PERFECT_IDENTIFIER, binding.activityCanvasPixelGridView.pixelPerfectMode)
        apply()
    }
}