package com.realtomjoney.pyxlmoose.converters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream

object BitmapConverter {
    fun convertStringToBitmap(str: String): Bitmap? {
        return try {
            val byte: ByteArray = Base64.decode(str, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(Base64.decode(str, Base64.DEFAULT), 0, byte.size)
        } catch (exception: Exception) {
            exception.printStackTrace()
            null
        }
    }

    fun convertBitmapToString(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)

        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT)
    }
}