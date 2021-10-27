package com.realtomjoney.pyxlmoose

import android.graphics.Bitmap

object BitmapDatabase {
    private val database = mutableListOf<SavedPixelArt>()

    fun addBitmap(param: SavedPixelArt) {
        database.add(param)
    }

    fun toList() = database.toList()
}