package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import android.view.MotionEvent
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.utility.constants.Flags

fun PixelGridView.extendedOnTouchEvent(event: MotionEvent): Boolean {
    val coordinateX = (event.x / scaleWidth).toInt()
    val coordinateY = (event.y / scaleHeight).toInt()

    caller.dispatchTouchEvent()

    when (event.action) {
        MotionEvent.ACTION_MOVE -> {
            if (!Flags.DisableActionMove) {
                if (coordinateX in 0 until pixelGridViewBitmap.width && coordinateY in 0 until pixelGridViewBitmap.height) {
                    caller.onPixelTapped(Coordinates(coordinateX, coordinateY))
                } else {
                    prevX = null
                    prevY = null
                }
            }
        }
        MotionEvent.ACTION_DOWN -> {
            if (coordinateX in 0 until pixelGridViewBitmap.width && coordinateY in 0 until pixelGridViewBitmap.height) {
                caller.onPixelTapped(Coordinates(coordinateX, coordinateY))
            } else {
                prevX = null
                prevY = null
            }
        }
        MotionEvent.ACTION_UP -> {
            caller.onActionUp()
        }
    }

    invalidate()

    return true
}