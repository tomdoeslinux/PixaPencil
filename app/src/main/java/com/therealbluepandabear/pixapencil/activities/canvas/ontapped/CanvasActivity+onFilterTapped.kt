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

package com.therealbluepandabear.pixapencil.activities.canvas.ontapped

import android.graphics.Color
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvascommands.applyBitmapFilter
import com.therealbluepandabear.pixapencil.activities.canvas.getSelectedColor
import com.therealbluepandabear.pixapencil.activities.canvas.judgeUndoRedoStacks
import com.therealbluepandabear.pixapencil.utility.general.ColorFilterUtilities
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun CanvasActivity.extendedOnFilterTapped(filterType: String) {
    when (filterType) {
        StringConstants.Identifiers.COLOR_FILTER_IDENTIFIER -> {
            canvasCommandsHelperInstance.applyBitmapFilter { ColorFilterUtilities.blendColor(it, getSelectedColor()) }
        }

        StringConstants.Identifiers.DARKEN_FILTER_IDENTIFIER -> {
            canvasCommandsHelperInstance.applyBitmapFilter { ColorFilterUtilities.blendColor(it, Color.BLACK) }
        }

        StringConstants.Identifiers.LIGHTEN_FILTER_IDENTIFIER -> {
            canvasCommandsHelperInstance.applyBitmapFilter { ColorFilterUtilities.blendColor(it, Color.WHITE) }
        }

        StringConstants.Identifiers.INVERT_FILTER_IDENTIFIER -> {
            canvasCommandsHelperInstance.applyBitmapFilter { ColorFilterUtilities.inverseRGB(it) }
        }

        StringConstants.Identifiers.GRAYSCALE_FILTER_IDENTIFIER -> {
            canvasCommandsHelperInstance.applyBitmapFilter { ColorFilterUtilities.grayScale(it) }
        }
    }

    judgeUndoRedoStacks()
}


