package com.therealbluepandabear.pixapencil.enums

import android.graphics.Bitmap
import android.os.Build

enum class BitmapCompressFormat(val correspondingEnum: () -> Bitmap.CompressFormat?) {
    PNG ({
        Bitmap.CompressFormat.PNG
    }),

    JPEG({
        Bitmap.CompressFormat.JPEG
    }),

    WEBP({
        Bitmap.CompressFormat.WEBP
    }),

    WEBP_LOSSLESS({
        if (Build.VERSION.SDK_INT >= 30) {
            Bitmap.CompressFormat.WEBP_LOSSLESS
        } else {
            Bitmap.CompressFormat.WEBP
        }
    }),

    WEBP_LOSSY({
        if (Build.VERSION.SDK_INT >= 30) {
            Bitmap.CompressFormat.WEBP_LOSSY
        } else {
            Bitmap.CompressFormat.WEBP
        }
    }),

    TIFF({
        null
    })
}