package com.realtomjoney.pyxlmoose

object PixelArtDatabase {
    private val database = mutableListOf<SavedPixelArt>()

    fun addItem(param: SavedPixelArt) = database.add(param)

    fun removeItem(index: Int) = database.removeAt(index)

    fun toList() = database.toList()
}