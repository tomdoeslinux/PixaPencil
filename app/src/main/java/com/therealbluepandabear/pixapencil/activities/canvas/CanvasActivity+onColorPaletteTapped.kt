package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.adapters.ColorPickerAdapter
import com.therealbluepandabear.pixapencil.models.ColorPalette

fun CanvasActivity.extendedOnColorPaletteTapped(selectedColorPalette: ColorPalette) {
    binding.activityCanvasColorPickerRecyclerView.adapter = ColorPickerAdapter(selectedColorPalette, this)
}