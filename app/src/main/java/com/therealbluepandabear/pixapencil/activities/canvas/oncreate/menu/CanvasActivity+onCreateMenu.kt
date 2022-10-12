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

package com.therealbluepandabear.pixapencil.activities.canvas.oncreate.menu

import android.view.Menu
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.enums.SymmetryMode
import com.therealbluepandabear.pixapencil.extensions.disable
import com.therealbluepandabear.pixapencil.utility.constants.Flags

fun CanvasActivity.onCreateMenu(_menu: Menu?): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.activity_canvas_top_app_menu, _menu)

    if (_menu != null) {
        menu = _menu
        menu.findItem(R.id.activityCanvasTopAppMenu_pixel_perfect_item).isChecked = binding.activityCanvasDrawingView.pixelPerfectMode
        menu.findItem(R.id.activityCanvasTopAppMenu_grid_item).isChecked = binding.activityCanvasDrawingView.gridEnabled
        menu.findItem(R.id.appMenu_symmetry_none_subItem).isChecked = true

        if (width != height) {
            menu.findItem(R.id.appMenu_symmetry_octal_subItem).isEnabled = false
        }

        if (width > 500 || height > 500) {
            menu.findItem(R.id.activityCanvasTopAppMenu_grid_item).isEnabled = false
            menu.findItem(R.id.activityCanvasTopAppMenu_grid_item).isChecked = false

            binding.activityCanvasDrawingView.gridEnabled = false
            binding.activityCanvasDrawingView.invalidate()

            Flags.DisableGridSharedPreferenceChanges = true
        }

        menu.findItem(R.id.activityCanvasTopAppMenu_undo).disable()
        menu.findItem(R.id.activityCanvasTopAppMenu_redo_item).disable()

        when (viewModel.currentSymmetryMode) {
            SymmetryMode.Horizontal -> {
                menu.findItem(R.id.appMenu_symmetry_horizontal_subItem).isChecked = true
            }

            SymmetryMode.Vertical -> {
                menu.findItem(R.id.appMenu_symmetry_vertical_subItem).isChecked = true
            }

            SymmetryMode.Quad -> {
                menu.findItem(R.id.appMenu_symmetry_quad_subItem).isChecked = true
            }

            SymmetryMode.Octal -> {
                menu.findItem(R.id.appMenu_symmetry_octal_subItem).isChecked = true
            }

            else -> {}
        }
    }

    return true
}