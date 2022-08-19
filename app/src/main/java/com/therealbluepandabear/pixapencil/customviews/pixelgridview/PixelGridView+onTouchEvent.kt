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
                    caller.onPixelTapped(Coordinates(coordinateX, coordinateY))
            }
        }
        MotionEvent.ACTION_DOWN -> {
                caller.onPixelTapped(Coordinates(coordinateX, coordinateY))

        }
        MotionEvent.ACTION_UP -> {
            caller.onActionUp()
        }
    }

    invalidate()

    return true
}