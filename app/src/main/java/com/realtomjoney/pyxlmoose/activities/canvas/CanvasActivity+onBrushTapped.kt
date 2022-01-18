package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.models.Brush

fun extendedOnBrushTapped(selectedBrush: Brush) {
    canvasInstance.myCanvasViewInstance.currentBrush = selectedBrush
}
