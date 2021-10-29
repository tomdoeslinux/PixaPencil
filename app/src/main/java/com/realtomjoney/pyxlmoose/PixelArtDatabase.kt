package com.realtomjoney.pyxlmoose

object PixelArtDatabase : Database<PixelArt> {
    private val database = mutableListOf<PixelArt>()

    override fun addItem(item: PixelArt) { database.add(item) }

    override fun removeItem(item: PixelArt) { database.remove(item) }

    override fun toList() = database.toList()
}