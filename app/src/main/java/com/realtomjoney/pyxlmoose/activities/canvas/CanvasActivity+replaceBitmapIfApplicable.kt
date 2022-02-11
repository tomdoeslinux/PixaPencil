package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.converters.BitmapConverter
import com.realtomjoney.pyxlmoose.database.AppData

fun CanvasActivity.replaceBitmapIfApplicable() {
    if (index != -1) {
        AppData.pixelArtDB.pixelArtCreationsDao().getAllPixelArtCreations().observe(context) {
            currentPixelArtObj = it[index!!]
            outerCanvasInstance.canvasFragment.myCanvasViewInstance.replaceBitmap(BitmapConverter.convertStringToBitmap(currentPixelArtObj.bitmap)!!)
            outerCanvasInstance.rotate(it[index!!].rotation.toInt(), false)
        }
    }
}