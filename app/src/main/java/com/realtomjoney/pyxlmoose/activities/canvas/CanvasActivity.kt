package com.realtomjoney.pyxlmoose.activities.canvas

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.realtomjoney.pyxlmoose.converters.JsonConverter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.extensions.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.showSnackbarWithAction
import com.realtomjoney.pyxlmoose.fragments.colorpicker.ColorPickerFragment
import com.realtomjoney.pyxlmoose.listeners.*
import com.realtomjoney.pyxlmoose.models.Brush
import com.realtomjoney.pyxlmoose.models.ColorPalette
import com.realtomjoney.pyxlmoose.models.Coordinates

class CanvasActivity :
    AppCompatActivity(),
    CanvasFragmentListener,
    ColorPickerListener,
    ColorPickerFragmentListener,
    FindAndReplaceFragmentListener,
    ToolsFragmentListener,
    FiltersFragmentListener,
    ColorPalettesFragmentListener,
    NewColorPaletteFragmentListener,
    BrushesFragmentListener,
    SprayToolSettingsFragmentListener {

    var previousView: View? = null

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreate()
    }

    fun initColorPickerFragmentInstance(colorPaletteMode: Boolean) = ColorPickerFragment.newInstance(getSelectedColor(), colorPaletteMode)

    override fun onCreateOptionsMenu(menu: Menu?) = extendedOnCreateOptionsMenu(menu)

    override fun onOptionsItemSelected(item: MenuItem) = extendedOnOptionsItemSelected(item)

    fun getSelectedColor() = extendedGetSelectedColor()

    fun setPixelColor(color: Int) = if (isPrimaryColorSelected) setPrimaryPixelColor(color) else setSecondaryPixelColor(color)

    override fun onPause() {
        extendedOnPause()
        super.onPause()
    }

    override fun onPixelTapped(coordinatesTapped: Coordinates) = extendedOnPixelTapped(coordinatesTapped)

    override fun onActionUp() = extendedOnActionUp()

    override fun onColorTapped(colorTapped: Int, view: View) = extendedOnColorTapped(colorTapped, view)

    override fun onColorLongTapped(colorPalette: ColorPalette, colorIndex: Int) {
        val extractedJson =
            JsonConverter.convertJsonStringToListOfInt(colorPalette.colorPaletteColorData).toMutableList()
        val color = extractedJson[colorIndex]
        extractedJson.removeAt(colorIndex)

        AppData.colorPalettesDB.colorPalettesDao().updateColorPaletteColorData(
            JsonConverter.convertListOfIntToJsonString(extractedJson), colorPalette.objId)

        val colorPaletteName = colorPalette.colorPaletteName

        binding.activityCanvasRootLayout.showSnackbarWithAction("Removed color from '$colorPaletteName'", SnackbarDuration.DEFAULT, "Undo") {
            extractedJson.add(colorIndex, color)

            AppData.colorPalettesDB.colorPalettesDao().updateColorPaletteColorData(
                JsonConverter.convertListOfIntToJsonString(extractedJson), colorPalette.objId)
        }
    }

    override fun onColorAdded(colorPalette: ColorPalette) = extendedOnAddColorTapped(colorPalette)

    override fun onDoneButtonPressed(selectedColor: Int, isColorPaletteMode: Boolean) = extendedOnDoneButtonPressed(selectedColor, isColorPaletteMode)

    override fun onDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) = extendedOnDoneButtonPressed(colorToFind, colorToReplace)

    override fun onBackPressed() = extendedOnBackPressed()

    override fun onToolTapped(toolName: String) = extendedOnToolTapped(toolName)

    override fun onFilterTapped(filterName: String) = extendedOnFilterSelected(filterName)

    override fun onColorPaletteTapped(selectedColorPalette: ColorPalette) = extendedOnColorPaletteTapped(selectedColorPalette)

    override fun onDoneButtonPressed(colorPaletteTitle: String, extractColorPaletteFromCanvas: Boolean) = extendedOnDoneButtonPressed(colorPaletteTitle, extractColorPaletteFromCanvas)

    override fun onBrushTapped(selectedBrush: Brush) = extendedOnBrushTapped(selectedBrush)

    override fun onDoneButtonPressed(radius: String, strength: String) = extendedOnDoneButtonPressed(radius, strength)
}
