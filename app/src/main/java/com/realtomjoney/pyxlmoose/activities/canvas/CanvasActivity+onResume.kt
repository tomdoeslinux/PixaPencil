package com.realtomjoney.pyxlmoose.activities.canvas

import android.os.Handler
import android.os.Looper
import com.realtomjoney.pyxlmoose.converters.BitmapConverter
import com.realtomjoney.pyxlmoose.utility.LongConstants

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