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

package com.therealbluepandabear.pixapencil.algorithms

import android.graphics.Bitmap
import android.graphics.Color
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.models.BitmapAction

class AlgorithmInfoParameter {
    lateinit var canvasCommandsHelperInstance: CanvasActivity.CanvasCommandsHelper
    lateinit var bitmap: Bitmap
    lateinit var currentBitmapAction: BitmapAction
    var color: Int = Color.BLACK

    companion object {
        fun create(_canvasCommandsHelperInstance: CanvasActivity.CanvasCommandsHelper, _bitmap: Bitmap, _currentBitmapAction: BitmapAction, _color: Int): AlgorithmInfoParameter {
            val toReturn = AlgorithmInfoParameter()
            toReturn.canvasCommandsHelperInstance = _canvasCommandsHelperInstance
            toReturn.bitmap = _bitmap
            toReturn.currentBitmapAction = _currentBitmapAction
            toReturn.color = _color

            return toReturn
        }
    }
}
