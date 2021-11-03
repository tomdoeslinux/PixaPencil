package com.realtomjoney.pyxlmoose.listeners

import com.realtomjoney.pyxlmoose.models.PixelArt

interface RecentCreationsListener {
    fun onCreationTapped(param: PixelArt)
    fun onCreationLongTapped(param: PixelArt)
}