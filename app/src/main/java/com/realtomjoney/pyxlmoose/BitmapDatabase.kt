package com.realtomjoney.pyxlmoose

import android.graphics.Bitmap

object BitmapDatabase {
    private val database = mutableListOf<Bitmap>()

    fun addBitmap(param: Bitmap) {
        database.add(param)
    }

    fun toList() = database.toList()
}