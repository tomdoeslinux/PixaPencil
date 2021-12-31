package com.realtomjoney.pyxlmoose.listeners

import com.realtomjoney.pyxlmoose.models.Brush

interface BrushesFragmentListener {
    fun onBrushTapped(selectedBrush: Brush)
}