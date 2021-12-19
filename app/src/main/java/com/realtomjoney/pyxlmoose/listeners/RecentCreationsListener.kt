package com.realtomjoney.pyxlmoose.listeners

import com.realtomjoney.pyxlmoose.models.PixelArt

interface RecentCreationsListener {
    fun onCreationTapped(pixelArtTapped: PixelArt)
    fun onCreationLongTapped(pixelArtLongTapped: PixelArt)
}