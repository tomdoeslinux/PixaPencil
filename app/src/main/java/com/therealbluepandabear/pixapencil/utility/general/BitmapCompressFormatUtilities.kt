package com.therealbluepandabear.pixapencil.utility.general

import android.graphics.Bitmap
import android.os.Build

object BitmapCompressFormatUtilities {
    fun getFormattedName(format: Bitmap.CompressFormat): String {
        return when {
            format == Bitmap.CompressFormat.PNG -> {
                "PNG"
            }

            format == Bitmap.CompressFormat.WEBP -> {
                "WEBP"
            }

            Build.VERSION.SDK_INT >= 30 && (format == Bitmap.CompressFormat.WEBP_LOSSLESS || format == Bitmap.CompressFormat.WEBP_LOSSY) -> {
                "WEBP"
            }

            else -> {
                "JPG"
            }
        }
    }
}