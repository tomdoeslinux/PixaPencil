package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.onGridOptionsItemSelected() {
    binding.activityCanvasPixelGridView.gridEnabled = !binding.activityCanvasPixelGridView.gridEnabled

    menu.findItem(R.id.activityCanvasTopAppMenu_grid_item).isChecked = binding.activityCanvasPixelGridView.gridEnabled

    with (sharedPreferenceObject.edit()) {
        putBoolean(StringConstants.Identifiers.SHARED_PREFERENCE_GRID_IDENTIFIER, binding.activityCanvasPixelGridView.gridEnabled)
        apply()
    }

    binding.activityCanvasPixelGridView.invalidate()
}