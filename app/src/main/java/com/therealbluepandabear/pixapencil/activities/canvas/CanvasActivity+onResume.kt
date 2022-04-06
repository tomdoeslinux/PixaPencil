package com.therealbluepandabear.pixapencil.activities.canvas

import android.os.Handler
import android.os.Looper
import com.therealbluepandabear.pixapencil.converters.BitmapConverter

fun CanvasActivity.extendedOnResume() {
    if (prevOrientation != 0) {
        if (prevOrientation != resources.configuration.orientation) {
            onOrientationChanged()
            prevOrientation = resources.configuration.orientation
        }
    }

    if (prevBitmapStr != "") {
        Handler(Looper.getMainLooper()).postDelayed({
            val convertedBMP = BitmapConverter.convertStringToBitmap(prevBitmapStr)
            if (convertedBMP != null) {
                outerCanvasInstance.canvasFragment.myCanvasViewInstance.replaceBitmap(convertedBMP)
            }
        }, 1000)
    }

}