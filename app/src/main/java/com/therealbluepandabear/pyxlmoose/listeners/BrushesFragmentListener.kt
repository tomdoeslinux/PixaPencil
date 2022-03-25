package com.therealbluepandabear.pyxlmoose.listeners

import com.therealbluepandabear.pyxlmoose.models.Brush

interface BrushesFragmentListener {
    fun onBrushTapped(selectedBrush: Brush)
}