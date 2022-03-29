package com.therealbluepandabear.pixapencil.listeners

import com.therealbluepandabear.pixapencil.models.Brush

interface BrushesListener {
    fun onBrushTapped(selectedBrush: Brush)
}