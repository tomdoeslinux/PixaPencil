package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import android.graphics.Bitmap
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun onExportToJPGOptionsItemSelected() {
    pixelGridViewInstance.saveAsImage(Bitmap.CompressFormat.JPEG)
}