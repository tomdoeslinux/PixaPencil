package com.therealbluepandabear.pixapencil.utility.general

import android.os.Build
import com.therealbluepandabear.pixapencil.enums.BitmapCompressFormat

object BitmapCompressFormatUtilities {
    fun getFormattedName(format: BitmapCompressFormat): String {
        return when {
            format == BitmapCompressFormat.PNG -> {
                "PNG"
            }

            format == BitmapCompressFormat.WEBP -> {
                "WEBP"
            }

            Build.VERSION.SDK_INT >= 30 && (format == BitmapCompressFormat.WEBP_LOSSLESS || format == BitmapCompressFormat.WEBP_LOSSY) -> {
                "WEBP"
            }

            format == BitmapCompressFormat.TIFF -> {
                "TIF"
            }

            format == BitmapCompressFormat.BMP -> {
                "BMP"
            }

            else -> {
                "JPG"
            }
        }
    }
}