package com.realtomjoney.pyxlmoose

import android.graphics.Bitmap

object BitmapDatabase {
    private val database = mutableMapOf<Bitmap, String>()

    fun addBitmap(param: Bitmap, str: String) {
        database[param] = str
    }

    fun toMap() = database.toMap()
}