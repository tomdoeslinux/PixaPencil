package com.therealbluepandabear.pixapencil.activities.canvas.ontapped

import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.models.Brush

fun CanvasActivity.extendedOnBrushTapped(selectedBrush: Brush) {
    viewModel.currentBrush = selectedBrush
}
