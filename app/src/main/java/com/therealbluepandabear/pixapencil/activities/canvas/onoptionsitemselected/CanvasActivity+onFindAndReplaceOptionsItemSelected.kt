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

package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import androidx.core.view.drawToBitmap
import androidx.fragment.app.commit
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers.drawPixelGridViewBitmap
import com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers.drawTransparentBackgroundViewBitmap
import com.therealbluepandabear.pixapencil.activities.canvas.selectedColorPaletteIndex
import com.therealbluepandabear.pixapencil.extensions.getColors
import com.therealbluepandabear.pixapencil.extensions.rotate
import com.therealbluepandabear.pixapencil.fragments.replacecolor.ReplaceColorFragment
import kotlin.math.abs
import kotlin.math.ceil

fun CanvasActivity.onFindAndReplaceOptionsItemSelected() {
    supportFragmentManager.commit {
        replace(
            R.id.activityCanvas_primaryFragmentHost, ReplaceColorFragment.newInstance(
                paramCanvasColors = binding.activityCanvasPixelGridView.pixelGridViewBitmap.getColors(),
                paramPixelGridViewBitmapSource = drawPixelGridViewBitmap(),
                paramTransparentBitmapSource = drawTransparentBackgroundViewBitmap(),
                paramSelectedColorPaletteIndex = selectedColorPaletteIndex,
                paramScaledWidth = binding.activityCanvasPixelGridView.drawToBitmap().rotate(binding.activityCanvasCardView.rotation.toInt(), viewModel.flipMatrix).width,
                paramScaledHeight =  binding.activityCanvasPixelGridView.drawToBitmap().rotate(binding.activityCanvasCardView.rotation.toInt(), viewModel.flipMatrix).height
            )
        )
        addToBackStack(null)
    }
}