package com.therealbluepandabear.pixapencil.customviews.pixelgridview

import android.view.MotionEvent
import com.therealbluepandabear.pixapencil.models.BitmapAction
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.utility.Flags

fun PixelGridView.extendedDispatchTouchEvent(event: MotionEvent): Boolean {
    val coordinateX = (event.x / scaleWidth).toInt()
    val coordinateY = (event.y / scaleHeight).toInt()

    if (currentBitmapAction == null) {
        currentBitmapAction = BitmapAction(mutableListOf())
    }

    when (event.actionMasked) {
        MotionEvent.ACTION_MOVE -> {
            if (!Flags.DisableActionMove) {
                if (coordinateX in 0 until canvasWidth && coordinateY in 0 until canvasHeight) {
                    caller.onPixelTapped(Coordinates(coordinateX, coordinateY))
                } else {
                    prevX = null
                    prevY = null
                }
            }
        }
        MotionEvent.ACTION_DOWN -> {
            if (coordinateX in 0 until canvasWidth && coordinateY in 0 until canvasHeight) {
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