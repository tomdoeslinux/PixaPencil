/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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