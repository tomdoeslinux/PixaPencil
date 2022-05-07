package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import android.graphics.Bitmap
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance

fun onExportToPNGOptionsItemSelected() {
    pixelGridViewInstance.saveAsImage(Bitmap.CompressFormat.PNG)
}