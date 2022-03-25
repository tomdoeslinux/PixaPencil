package com.therealbluepandabear.pyxlmoose.activities.canvas

import com.therealbluepandabear.pyxlmoose.models.Brush

fun extendedOnBrushTapped(selectedBrush: Brush) {
    outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBrush = selectedBrush
}
