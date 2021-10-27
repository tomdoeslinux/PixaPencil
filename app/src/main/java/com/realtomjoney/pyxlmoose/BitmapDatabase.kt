package com.realtomjoney.pyxlmoose

object BitmapDatabase {
    private val database = mutableListOf<SavedPixelArt>()

    fun addBitmap(param: SavedPixelArt) {
        database.add(param)
    }

    fun toList() = database.toList()
}