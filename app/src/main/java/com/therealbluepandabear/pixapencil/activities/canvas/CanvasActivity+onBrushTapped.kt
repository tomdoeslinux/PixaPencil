package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.Brush

fun extendedOnBrushTapped(selectedBrush: Brush) {
    pixelGridViewInstance.currentBrush = selectedBrush
}
