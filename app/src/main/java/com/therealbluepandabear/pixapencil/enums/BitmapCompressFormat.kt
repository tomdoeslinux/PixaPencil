package com.therealbluepandabear.pixapencil.enums

import android.graphics.Bitmap
import android.os.Build
import com.tianscar.quickbitmap.BitmapEncoder

enum class BitmapCompressFormat(val correspondingEnum: () -> Bitmap.CompressFormat?, val secondaryCorrespondingEnum: BitmapEncoder.CompressFormat?) {
    PNG ({
        Bitmap.CompressFormat.PNG
    }, null),

    JPEG({
        Bitmap.CompressFormat.JPEG
    }, null),

    WEBP({
        Bitmap.CompressFormat.WEBP
    }, null),

    WEBP_LOSSLESS({
        if (Build.VERSION.SDK_INT >= 30) {
            Bitmap.CompressFormat.WEBP_LOSSLESS
        } else {
            Bitmap.CompressFormat.WEBP
        }
    }, null),

    WEBP_LOSSY({
        if (Build.VERSION.SDK_INT >= 30) {
            Bitmap.CompressFormat.WEBP_LOSSY
        } else {
            Bitmap.CompressFormat.WEBP
        }
    }, null),

    TIFF({
        null
    }, null),

    BMP({
        null
    }, BitmapEncoder.CompressFormat.BMP)
}