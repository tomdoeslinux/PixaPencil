package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import android.graphics.Color
import android.os.Build
import android.view.MenuItem
import androidx.constraintlayout.widget.ConstraintLayout
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.binding
import com.therealbluepandabear.pixapencil.activities.canvas.selectedColorPaletteIndex
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.enums.BitmapCompressFormat
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.enums.SymmetryMode
import com.therealbluepandabear.pixapencil.extensions.activity
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.therealbluepandabear.pixapencil.extensions.showSnackbarWithAction
import com.therealbluepandabear.pixapencil.fragments.canvas.pixelGridViewInstance
import com.therealbluepandabear.pixapencil.models.ColorPalette
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


const val ZOOM_INCREMENT = 0.2f

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

        R.id.activityCanvasTopAppMenu_reset_position_subItem -> {
            outerCanvasInstance.resetPosition()
        }

        R.id.activityCanvasTopAppMenu_save_in_background_item -> {
            onSaveProjectOptionsItemSelected(true)
        }

        R.id.activityCanvasTopAppMenu_import_lospec_palette_item -> {
            val details: ConstraintLayout =
                activity()?.layoutInflater?.inflate(R.layout.import_lospec_palette_alert, activity()?.findViewById(android.R.id.content),false)
                        as ConstraintLayout

            showDialog(
                getString(R.string.activityCanvasTopAppMenu_import_lospec_palette_str),
                null,
                getString(R.string.generic_ok_in_code_str), { _, _ ->
                    val queue = Volley.newRequestQueue(this)
                    val url = "https://lospec.com/palette-list/${details.findViewById<TextInputEditText>(R.id.importLospecPaletteAlert_palette_url_identifier_textInputEditText).text.toString()}.json"

                    val stringRequest = StringRequest(Request.Method.GET, url,
                        { response ->
                            val mJsonObject = JsonParser.parseString(response).asJsonObject

                            val colorPaletteTitle = Gson().fromJson(mJsonObject.get("name"), String::class.java)
                            val colorPaletteColorData1 = Gson().fromJson(mJsonObject.get("colors"), Array<String>::class.java).toList()
                            val colorPaletteColorData2 = mutableListOf<Int>()

                            for (i in colorPaletteColorData1) {
                                colorPaletteColorData2.add(Color.parseColor("#$i"))
                            }

                            val colorPalette = ColorPalette(colorPaletteTitle, JsonConverter.convertListToJsonString(colorPaletteColorData2))

                            CoroutineScope(Dispatchers.IO).launch {
                                AppData.colorPalettesDB.colorPalettesDao().insertColorPalette(colorPalette)
                            }

                            binding.root.post {
                                binding.clayout!!.showSnackbarWithAction(getString(R.string.snackbar_successfully_imported_lospec_palette_in_code_str, colorPaletteTitle), SnackbarDuration.Medium, getString(R.string.generic_switch_in_code_str)) {
                                    AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this) {
                                        selectedColorPaletteIndex = it.size - 1
                                    }
                                    onColorPaletteTapped(colorPalette)
                                }
                            }
                        }, {

                        })

                    queue.add(stringRequest)

                }, null, null, view = details
            )
        }
    }
    return true
}