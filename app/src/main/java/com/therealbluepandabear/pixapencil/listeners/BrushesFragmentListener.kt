package com.therealbluepandabear.pixapencil.listeners

import com.therealbluepandabear.pixapencil.models.Brush

interface BrushesFragmentListener {
    fun onBrushTapped(selectedBrush: Brush)
}