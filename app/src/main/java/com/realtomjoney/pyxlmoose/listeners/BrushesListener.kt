package com.realtomjoney.pyxlmoose.listeners

import com.realtomjoney.pyxlmoose.models.Brush

interface BrushesListener {
    fun onBrushTapped(selectedBrush: Brush)
}