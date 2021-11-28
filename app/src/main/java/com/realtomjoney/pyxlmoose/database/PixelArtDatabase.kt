package com.realtomjoney.pyxlmoose.database

import com.realtomjoney.pyxlmoose.models.PixelArt

object PixelArtDatabase : Database<PixelArt> {
    private val database = mutableListOf<PixelArt>()

    override fun addItem(item: PixelArt) { database.add(item) }

    override fun removeItem(item: PixelArt) { database.remove(item) }

    override fun toList() = database.toList()

    override fun replaceItemByIndex(index: Int, newItem: PixelArt) {
        database[index] = newItem
    }
}