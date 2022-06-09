package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import android.os.Build
import android.view.MenuItem
import android.widget.Toast
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.*
import com.therealbluepandabear.pixapencil.converters.BitmapConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.enums.BitmapCompressFormat
import com.therealbluepandabear.pixapencil.enums.SymmetryMode
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.utility.constants.ObjectConstants

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
            pixelGridViewInstance.saveAsImage(BitmapCompressFormat.PNG)
        }

        R.id.activityCanvasTopAppMenu_export_to_jpg_item -> {
            pixelGridViewInstance.saveAsImage(BitmapCompressFormat.JPEG)
        }

        R.id.activityCanvasTopAppMenu_export_to_webp_item -> {
            if (Build.VERSION.SDK_INT >= 30) {
                pixelGridViewInstance.saveAsImage(BitmapCompressFormat.WEBP_LOSSLESS)
            } else {
                pixelGridViewInstance.saveAsImage(BitmapCompressFormat.WEBP)
            }
        }

        R.id.activityCanvasTopAppMenu_export_to_tiff_item -> {
            pixelGridViewInstance.saveAsImage(BitmapCompressFormat.TIFF)
        }

        R.id.activityCanvasTopAppMenu_export_to_bmp_item -> {
            pixelGridViewInstance.saveAsImage(BitmapCompressFormat.BMP)
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
            viewModel.currentSymmetryMode = SymmetryMode.defaultSymmetryMode
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
            onSaveProjectOptionsItemSelected(true)

            if (ObjectConstants.CurrentPixelArtObj.bitmap == BitmapConverter.convertBitmapToString(pixelGridViewInstance.pixelGridViewBitmap)) {
                Toast.makeText(
                    this@extendedOnOptionsItemSelected,
                    getString(R.string.generic_saved_in_code_str),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    return true
}