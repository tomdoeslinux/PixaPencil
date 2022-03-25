package com.therealbluepandabear.pyxlmoose.listeners

import com.therealbluepandabear.pyxlmoose.models.PixelArt

interface RecentCreationsListener {
    fun onCreationTapped(creationTapped: PixelArt)
    fun onCreationLongTapped(creationTapped: PixelArt)
}