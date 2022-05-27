package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import android.graphics.Bitmap
import android.os.Build
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun onExportToWEBPOptionsItemSelected() {
    if (Build.VERSION.SDK_INT >= 30) {
        pixelGridViewInstance.saveAsImage(Bitmap.CompressFormat.WEBP_LOSSLESS)
    } else {
        pixelGridViewInstance.saveAsImage(Bitmap.CompressFormat.WEBP)
    }
}