package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.models.Brush

fun extendedOnBrushTapped(selectedBrush: Brush) {
    outerCanvasInstance.canvasFragment.pixelGridViewInstance.currentBrush = selectedBrush
}
