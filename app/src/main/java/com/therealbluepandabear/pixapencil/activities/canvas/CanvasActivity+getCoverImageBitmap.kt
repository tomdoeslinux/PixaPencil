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

package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Bitmap
import android.graphics.Color
import androidx.core.view.drawToBitmap
import com.therealbluepandabear.pixapencil.enums.OverlayType
import com.therealbluepandabear.pixapencil.extensions.isRect
import com.therealbluepandabear.pixapencil.extensions.isWidthLarger
import com.therealbluepandabear.pixapencil.extensions.overlay
import com.therealbluepandabear.pixapencil.extensions.rotate

var gridWasEnabled = false

fun CanvasActivity.disableGridIfNeeded() {
    if (binding.activityCanvasPixelGridView.gridEnabled) {
        binding.activityCanvasPixelGridView.gridEnabled = false
        binding.activityCanvasPixelGridView.invalidate()
        gridWasEnabled = true
    } else {
        gridWasEnabled = false
    }
}

fun CanvasActivity.enableGridIfNeeded() {
    if (gridWasEnabled) {
        binding.activityCanvasPixelGridView.gridEnabled = true
        binding.activityCanvasPixelGridView.invalidate()
    }
}

fun CanvasActivity.createBasicCoverBitmap(): Bitmap {
    val primaryBitmap = binding.activityCanvasPixelGridView.drawToBitmap()
    val transparentBackgroundBitmap = binding.activityCanvasTransparentBackgroundView.drawToBitmap()
    return transparentBackgroundBitmap.overlay(primaryBitmap)
}

fun CanvasActivity.getCoverImageBitmap(): Bitmap {
    disableGridIfNeeded()
    var coverBitmap = createBasicCoverBitmap()

    if (coverBitmap.isRect()) {
        val bitmapBaseDimension = if (coverBitmap.isWidthLarger()) {
            binding.activityCanvasCardView.width
        } else {
            binding.activityCanvasCardView.height
        }

        val bitmap = Bitmap.createBitmap(
            bitmapBaseDimension,
            bitmapBaseDimension,
            Bitmap.Config.ARGB_8888
        )
        bitmap.eraseColor(Color.WHITE)
        coverBitmap = bitmap.overlay(coverBitmap, OverlayType.CenterSecond)
    }

    val bmps: Bitmap?
    bmps = coverBitmap.rotate(viewModel.currentRotation.toInt(), viewModel.flipMatrix)

    enableGridIfNeeded()

    return bmps
}