package com.realtomjoney.pyxlmoose.listeners

import com.realtomjoney.pyxlmoose.models.PixelArts

interface RecentCreationsListener {
    fun onCreationTapped(param: PixelArts)
    fun onCreationLongTapped(param: PixelArts)
}