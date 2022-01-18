package com.realtomjoney.pyxlmoose.listeners

import com.realtomjoney.pyxlmoose.models.PixelArt

interface RecentCreationsListener {
    fun onCreationTapped(creationTapped: PixelArt)
    fun onCreationLongTapped(creationTapped: PixelArt)
}