package com.realtomjoney.pyxlmoose

object PixelArtDatabase {
    private val database = mutableListOf<PixelArt>()

    fun addItem(param: PixelArt) = database.add(param)

    fun removeItem(index: Int) = database.removeAt(index)

    fun toList() = database.toList()
}