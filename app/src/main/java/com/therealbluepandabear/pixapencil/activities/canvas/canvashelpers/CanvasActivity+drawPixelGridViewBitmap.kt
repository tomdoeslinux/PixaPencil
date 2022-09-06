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

package com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers

import android.graphics.Bitmap
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.extensions.rotate
import kotlin.math.abs
import kotlin.math.ceil

/**
 * We are using 'ceil' to ensure that there is no crooked rotation effect, as when the user
 * selects '180 degrees', they are actually rotating the canvas by 179.9 degrees, I made this the behavior due to a
 * bug in the SDK in which when you rotate the canvas 180 degrees, the drop shadow is lost.
 * Thus, if we had no 'ceil', the resulting bitmap would be crooked as for some reason it rounds it down in this situation.
 */

fun CanvasActivity.drawPixelGridViewBitmap(): Bitmap {
    return binding.activityCanvasPixelGridView.pixelGridViewBitmap
        .rotate(ceil(abs(binding.activityCanvasCardView.rotation)).toInt(), viewModel.flipMatrix)
}