package com.therealbluepandabear.pixapencil.activities.canvas

import android.os.Handler
import android.os.Looper
import com.therealbluepandabear.pixapencil.converters.BitmapConverter
import com.therealbluepandabear.pixapencil.utility.LongConstants

fun CanvasActivity.extendedOnResume() {
    if (prevBitmapStr != "") {
        Handler(Looper.getMainLooper()).postDelayed({
            val convertedBMP = BitmapConverter.convertStringToBitmap(prevBitmapStr)
            if (convertedBMP != null) {
                outerCanvasInstance.canvasFragment.myCanvasViewInstance.replaceBitmap(convertedBMP)
            }
        }, LongConstants.DefaultHandlerDelay)
    }

    if (prevOrientation != 0) {
        if (prevOrientation != resources.configuration.orientation) {
            onOrientationChanged()
            prevOrientation = resources.configuration.orientation
        }
    }
}