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

import android.view.MenuItem
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected.*
import com.therealbluepandabear.pixapencil.enums.FlipValue
import com.therealbluepandabear.pixapencil.enums.SymmetryMode

fun CanvasActivity.onMenuItemSelected(item: MenuItem): Boolean {

    when (item.itemId) {
        R.id.activityCanvasTopAppMenu_zoom_out_item -> {
            onZoomOutOptionsItemSelected()
        }

        R.id.activityCanvasTopAppMenu_zoom_in_item -> {
            onZoomInOptionsItemSelected()
        }

        R.id.activityMainTopAppMenu_save_project_item -> {
            onSaveProjectOptionsItemSelected()
        }

        R.id.activityCanvasTopAppMenu_undo -> {
            onUndoOptionsItemSelected()
        }

        R.id.activityCanvasTopAppMenu_redo_item -> {
            onRedoOptionsItemSelected()
        }

        R.id.activityCanvasTopAppMenu_new_color_palette_item -> {
            onNewColorPaletteOptionsItemSelected()
        }

        R.id.activityCanvasTopAppMenu_pixel_perfect_item -> {
            onPixelPerfectOptionsItemSelected()
        }

        R.id.activityCanvasTopAppMenu_export_item -> {
            onExportOptionsItemSelected()
        }

        R.id.appMenu_rotate_90_degrees_clockwise_subItem -> {
            onRotate90DegreesOptionsItemSelected()
        }

        R.id.appMenu_rotate_180_degrees_clockwise_subItem -> {
            onRotate180DegreesOptionsItemSelected()
        }

        R.id.appMenu_rotate_90_degrees_anti_clockwise_subItem -> {
            onRotate90DegreesAntiClockwiseOptionsItemSelected()
        }

        R.id.activityCanvasTopAppMenu_reset_zoom_and_pos_subItem -> {
            onResetZoomOptionsItemSelected()
        }

        R.id.activityCanvasTopAppMenu_clear_canvas_item -> {
            onClearCanvasOptionsItemSelected()
        }

        R.id.activityCanvasTopAppMenu_replace_color_item -> {
            onFindAndReplaceOptionsItemSelected()
        }

        R.id.activityCanvasTopAppMenu_grid_item -> {
            onGridOptionsItemSelected()
        }

        R.id.appMenu_symmetry_none_subItem -> {
            menu.findItem(R.id.appMenu_symmetry_none_subItem).isChecked = true
            viewModel.currentSymmetryMode = SymmetryMode.None
        }

        R.id.appMenu_symmetry_horizontal_subItem -> {
            menu.findItem(R.id.appMenu_symmetry_horizontal_subItem).isChecked = true
            viewModel.currentSymmetryMode = SymmetryMode.Horizontal
        }

        R.id.appMenu_symmetry_vertical_subItem -> {
            menu.findItem(R.id.appMenu_symmetry_vertical_subItem).isChecked = true
            viewModel.currentSymmetryMode = SymmetryMode.Vertical
        }

        R.id.appMenu_symmetry_quad_subItem -> {
            menu.findItem(R.id.appMenu_symmetry_quad_subItem).isChecked = true
            viewModel.currentSymmetryMode = SymmetryMode.Quad
        }

        R.id.appMenu_symmetry_octal_subItem -> {
            menu.findItem(R.id.appMenu_symmetry_octal_subItem).isChecked = true
            viewModel.currentSymmetryMode = SymmetryMode.Octal
        }

        R.id.activityCanvasTopAppMenu_save_in_background_item -> {
            onSaveInBackgroundOptionsItemSelected()
        }

        R.id.activityCanvasTopAppMenu_import_lospec_palette_item -> {
            onImportLospecPaletteOptionsItemSelected()
        }

        R.id.appMenu_flip_horizontal_subItem -> {
        }

        R.id.appMenu_flip_vertical_subItem -> {
        }
    }
    return true
}