package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import android.view.MenuItem
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.*
import com.therealbluepandabear.pixapencil.enums.SymmetryMode
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.utility.StringConstants

const val zoomIncrement = 0.2f

fun CanvasActivity.extendedOnOptionsItemSelected(item: MenuItem): Boolean {

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

        R.id.activityCanvasTopAppMenu_export_to_png_item -> {
            onExportToPNGOptionsItemSelected()
        }

        R.id.activityCanvasTopAppMenu_export_to_jpg_item -> {
            onExportToJPGOptionsItemSelected()
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

        R.id.activityCanvasTopAppMenu_reset_zoom_subItem -> {
            onResetZoomOptionsItemSelected()
        }

        R.id.activityCanvasTopAppMenu_clear_canvas_item -> {
            onClearCanvasOptionsItemSelected()
        }

        R.id.activityCanvasTopAppMenu_find_and_replace_item -> {
            onFindAndReplaceOptionsItemSelected()
        }

        R.id.activityCanvasTopAppMenu_grid_item -> {
            onGridOptionsItemSelected()
        }

        R.id.appMenu_symmetry_none_subItem -> {
            menu.findItem(R.id.appMenu_symmetry_none_subItem).isChecked = true
            pixelGridViewInstance.symmetryMode = SymmetryMode.defaultSymmetryMode
        }

        R.id.appMenu_symmetry_horizontal_subItem -> {
            menu.findItem(R.id.appMenu_symmetry_horizontal_subItem).isChecked = true
            pixelGridViewInstance.symmetryMode = SymmetryMode.Horizontal
        }

        R.id.appMenu_symmetry_vertical_subItem -> {
            menu.findItem(R.id.appMenu_symmetry_vertical_subItem).isChecked = true
            pixelGridViewInstance.symmetryMode = SymmetryMode.Vertical
        }

        R.id.appMenu_symmetry_quad_subItem -> {
            menu.findItem(R.id.appMenu_symmetry_quad_subItem).isChecked = true
            pixelGridViewInstance.symmetryMode = SymmetryMode.Quad
        }

        R.id.appMenu_symmetry_octal_subItem -> {
            menu.findItem(R.id.appMenu_symmetry_octal_subItem).isChecked = true
            pixelGridViewInstance.symmetryMode = SymmetryMode.Octal
        }
    }
    return true
}