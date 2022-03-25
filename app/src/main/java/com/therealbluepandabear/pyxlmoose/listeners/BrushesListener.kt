package com.therealbluepandabear.pyxlmoose.listeners

import com.therealbluepandabear.pyxlmoose.models.Brush

interface BrushesListener {
    fun onBrushTapped(selectedBrush: Brush)
}