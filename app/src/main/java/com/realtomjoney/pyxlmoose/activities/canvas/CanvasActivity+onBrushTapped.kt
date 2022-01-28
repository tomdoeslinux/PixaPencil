package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.models.Brush

fun extendedOnBrushTapped(selectedBrush: Brush) {
    outerCanvasInstance.canvasFragment.myCanvasViewInstance.currentBrush = selectedBrush
}
