package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.menu
import com.therealbluepandabear.pixapencil.activities.canvas.sharedPreferenceObject
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.utility.StringConstants

fun CanvasActivity.onGridOptionsItemSelected() {
    pixelGridViewInstance.gridEnabled = !pixelGridViewInstance.gridEnabled

    menu.findItem(R.id.activityCanvasTopAppMenu_grid_item).isChecked = pixelGridViewInstance.gridEnabled

    with (sharedPreferenceObject.edit()) {
        putBoolean(StringConstants.Identifiers.SharedPreferenceGridIdentifier, pixelGridViewInstance.gridEnabled)
        apply()
    }

    pixelGridViewInstance.invalidate()
}