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

import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.oncreate.menu.ZOOM_INCREMENT
import com.therealbluepandabear.pixapencil.extensions.disable
import com.therealbluepandabear.pixapencil.extensions.enable
import java.math.RoundingMode

fun CanvasActivity.onZoomOutOptionsItemSelected() {
    binding.activityCanvasCardView.scaleX -= ZOOM_INCREMENT
    binding.activityCanvasCardView.scaleY -= ZOOM_INCREMENT

    val roundedZoom = ZOOM_INCREMENT.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()

    if (binding.activityCanvasCardView.scaleX - ZOOM_INCREMENT < roundedZoom) {
        menu.findItem(R.id.activityCanvasTopAppMenu_zoom_out_item).disable()
    } else {
        menu.findItem(R.id.activityCanvasTopAppMenu_zoom_in_item).enable()
    }

    binding.activityCanvasPixelGridView.invalidate()
}