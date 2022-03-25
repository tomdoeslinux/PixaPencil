package com.therealbluepandabear.pyxlmoose.activities.canvas

import com.therealbluepandabear.pyxlmoose.adapters.ColorPickerAdapter
import com.therealbluepandabear.pyxlmoose.models.ColorPalette

fun CanvasActivity.extendedOnColorPaletteTapped(selectedColorPalette: ColorPalette) {
    binding.activityCanvasColorPickerRecyclerView.adapter = ColorPickerAdapter(selectedColorPalette, this)
}