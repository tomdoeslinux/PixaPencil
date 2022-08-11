package com.therealbluepandabear.pixapencil.activities.canvas.oncreate.menu

import android.app.AlertDialog
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doAfterTextChanged
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers.flip
import com.therealbluepandabear.pixapencil.activities.canvas.canvashelpers.resetPosition
import com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected.*
import com.therealbluepandabear.pixapencil.activities.canvas.selectedColorPaletteIndex
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.databinding.ExportProjectDialogLayoutBinding
import com.therealbluepandabear.pixapencil.enums.*
import com.therealbluepandabear.pixapencil.extensions.activity
import com.therealbluepandabear.pixapencil.extensions.showSnackbarWithAction
import com.therealbluepandabear.pixapencil.models.ColorPalette
import com.therealbluepandabear.pixapencil.utility.InputFilterMinMax
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val ZOOM_INCREMENT = 0.2f

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
            val exportLayoutBinding = ExportProjectDialogLayoutBinding.inflate(LayoutInflater.from(this))
            var selectedFileType: BitmapCompressFormat = BitmapCompressFormat.PNG

            exportLayoutBinding.root.post {
                // We do this so that there is no default top margin, which I personally find it ugly
                (exportLayoutBinding.root.layoutParams as ViewGroup.MarginLayoutParams).topMargin = 0

                exportLayoutBinding.exportProjectDialogLayoutFileNameTextInputEditText.setText(projectTitle)
                exportLayoutBinding.fragmentNewCanvasCompressionQualityTextInputEditText.setText(100.toString())
                exportLayoutBinding.exportProjectDialogLayoutFileNameTextInputEditText.doAfterTextChanged {
                    if (it.toString().isNotBlank()) {
                        exportLayoutBinding.exportProjectDialogLayoutFileNameTextInputLayout.isErrorEnabled = false
                    } else {
                        exportLayoutBinding.exportProjectDialogLayoutFileNameTextInputLayout.error = getString(R.string.exception_invalid_file_name)
                    }
                }

                exportLayoutBinding.fragmentNewCanvasCompressionQualityTextInputEditText.filters = arrayOf(InputFilterMinMax(0, 100))

                exportLayoutBinding.exportProjectDialogLayoutRadioGroupFileType.setOnCheckedChangeListener { _, optionId ->
                    if (optionId != R.id.exportProjectDialogLayout_radioButton_JPG) {
                        exportLayoutBinding.fragmentNewCanvasCompressionQualityTextInputLayout.visibility = View.GONE
                    } else {
                        exportLayoutBinding.fragmentNewCanvasCompressionQualityTextInputLayout.visibility = View.VISIBLE
                    }

                    when (optionId) {
                        R.id.exportProjectDialogLayout_radioButton_PNG -> {
                            selectedFileType = BitmapCompressFormat.PNG
                        }

                        R.id.exportProjectDialogLayout_radioButton_JPG -> {
                            selectedFileType = BitmapCompressFormat.JPEG
                        }

                        R.id.exportProjectDialogLayout_radioButton_WEBP -> {
                            selectedFileType = BitmapCompressFormat.WEBP
                        }

                        R.id.exportProjectDialogLayout_radioButton_TIF -> {
                            selectedFileType = BitmapCompressFormat.TIFF
                        }

                        R.id.exportProjectDialogLayout_radioButton_BMP -> {
                            selectedFileType = BitmapCompressFormat.BMP
                        }

                        else -> {
                            selectedFileType = BitmapCompressFormat.PNG
                        }
                    }
                }
            }

            val alertDialog = MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
                .setTitle(R.string.activityCanvasTopAppMenu_export)
                .setView(exportLayoutBinding.root)
                .setCancelable(false)
                .setPositiveButton(R.string.generic_ok, null)
                .setNegativeButton(R.string.generic_cancel, null)
                .show()

            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                val title = exportLayoutBinding.exportProjectDialogLayoutFileNameTextInputEditText.text.toString()

                if (title.isNotBlank()) {
                    val resolution: BitmapResolution =
                        when (exportLayoutBinding.exportProjectDialogLayoutRadioGroupResolutionType.checkedRadioButtonId) {
                            R.id.exportProjectDialogLayout_radioButton_Raw -> {
                                BitmapResolution.Raw
                            }

                            R.id.exportProjectDialogLayout_radioButton_Scaled -> {
                                BitmapResolution.Scaled
                            }

                            else -> {
                                BitmapResolution.Raw
                            }
                        }

                    binding.activityCanvasPixelGridView.saveAsImage(
                        selectedFileType,
                        resolution,
                        binding.activityCanvasCoordinatorLayout,
                        exportLayoutBinding.exportProjectDialogLayoutFileNameTextInputEditText.text.toString(),
                        viewModel.flipMatrix,
                        compressionOutputQuality = exportLayoutBinding.fragmentNewCanvasCompressionQualityTextInputEditText.text.toString().toIntOrNull() ?: 100
                    )

                    alertDialog.cancel()
                } else {
                    exportLayoutBinding.exportProjectDialogLayoutFileNameTextInputLayout.error = getString(R.string.exception_invalid_file_name)
                }
            }
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

        R.id.activityCanvasTopAppMenu_replace_color_item -> {
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
            if (originalX != null && originalY != null) {
                resetPosition()
            }
        }

        R.id.activityCanvasTopAppMenu_save_in_background_item -> {
            onSaveInBackgroundOptionsItemSelected()
        }

        R.id.activityCanvasTopAppMenu_import_lospec_palette_item -> {
            val details: ConstraintLayout =
                activity()?.layoutInflater?.inflate(R.layout.import_lospec_palette_alert, activity()?.findViewById(android.R.id.content),false)
                        as ConstraintLayout

            val alertDialog = MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
                .setTitle(R.string.activityCanvasTopAppMenu_import_lospec_palette)
                .setView(details)
                .setPositiveButton(R.string.generic_ok) { _, _ ->
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
                                binding.activityCanvasCoordinatorLayout.showSnackbarWithAction(getString(R.string.snackbar_successfully_imported_lospec_palette, colorPaletteTitle), SnackbarDuration.Medium, getString(R.string.generic_switch)) {
                                    AppData.colorPalettesDB.colorPalettesDao().getAllColorPalettes().observe(this) {
                                        selectedColorPaletteIndex = it.size - 1
                                    }
                                    onColorPaletteTapped(colorPalette)
                                }
                            }
                        }, {

                        })

                    queue.add(stringRequest)
                }
                .setNegativeButton(R.string.generic_cancel, null)

            alertDialog.show()
        }

        R.id.appMenu_flip_horizontal_subItem -> {
            flip(FlipValue.Horizontal)
        }

        R.id.appMenu_flip_vertical_subItem -> {
            flip(FlipValue.Vertical)
        }
    }
    return true
}