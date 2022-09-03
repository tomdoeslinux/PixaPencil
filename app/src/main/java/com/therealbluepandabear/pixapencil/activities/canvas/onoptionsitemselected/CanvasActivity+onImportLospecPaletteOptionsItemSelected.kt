package com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected

import android.graphics.Color
import android.view.LayoutInflater
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.databinding.ImportLospecPaletteAlertBinding
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showSnackbarWithAction
import com.therealbluepandabear.pixapencil.models.ColorPalette
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CanvasActivity.onImportLospecPaletteOptionsItemSelected() {
    val importLospecPaletteAlertBinding = ImportLospecPaletteAlertBinding.inflate(LayoutInflater.from(this))

    val alertDialog = MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
        .setTitle(R.string.activityCanvasTopAppMenu_import_lospec_palette)
        .setView(importLospecPaletteAlertBinding.root)
        .setPositiveButton(R.string.generic_ok) { _, _ ->
            if (importLospecPaletteAlertBinding.importLospecPaletteAlertPaletteUrlIdentifierTextInputEditText.text!!.isNotEmpty()) {
            val queue = Volley.newRequestQueue(this)
            val url = "https://lospec.com/palette-list/${importLospecPaletteAlertBinding.importLospecPaletteAlertPaletteUrlIdentifierTextInputEditText.text.toString()}.json"

            val stringRequest = StringRequest(
                Request.Method.GET, url,
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
                        binding.activityCanvasCoordinatorLayout.showSnackbarWithAction(getString(R.string.snackbar_successfully_imported_lospec_palette, colorPaletteTitle), SnackbarDuration.Medium, getString(
                            R.string.generic_switch)) {
                            onColorPaletteTapped(colorPalette)
                        }
                    }
                }, {

                })

                queue.add(stringRequest)
            }
        }
        .setNegativeButton(R.string.generic_cancel, null)

    alertDialog.show()
}