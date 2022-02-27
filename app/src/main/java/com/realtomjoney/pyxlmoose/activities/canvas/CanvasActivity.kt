package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Bitmap
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.realtomjoney.pyxlmoose.extensions.replacePixelsByColor
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

    private var previewColorToFind: Int? = null
    private var previewColorToReplace: Int? = null

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

    override fun onColorLongTapped(colorPalette: ColorPalette, colorIndex: Int) = extendedOnColorLongTapped(colorPalette, colorIndex)

    override fun onColorAdded(colorPalette: ColorPalette) = extendedOnAddColorTapped(colorPalette)

    override fun onDoneButtonPressed(selectedColor: Int, isColorPaletteMode: Boolean) = extendedOnDoneButtonPressed(selectedColor, isColorPaletteMode)

    override fun onColorToFindTapped(bitmap: Bitmap, colorToFind: Int): Bitmap {
        previewColorToFind = colorToFind

        val toReturn: Bitmap = if (previewColorToReplace != null) {
            val bmp = getCoverImageBitmap()
            bmp.replacePixelsByColor(previewColorToFind!!, previewColorToReplace!!)

            bmp
        } else {
            getCoverImageBitmap()
        }

        return toReturn
    }

    override fun onColorToReplaceTapped(bitmap: Bitmap, colorToReplace: Int): Bitmap {
        previewColorToReplace = colorToReplace

        val bmp = getCoverImageBitmap()
        bmp.replacePixelsByColor(previewColorToFind!!, previewColorToReplace!!)

        return bmp
    }

    override fun onDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) = extendedOnDoneButtonPressed(colorToFind, colorToReplace)

    override fun onBackPressed() = extendedOnBackPressed()

    override fun onToolTapped(toolName: String) = extendedOnToolTapped(toolName)

    override fun onFilterTapped(filterName: String) = extendedOnFilterSelected(filterName)

    override fun onColorPaletteTapped(selectedColorPalette: ColorPalette) = extendedOnColorPaletteTapped(selectedColorPalette)

    override fun onDoneButtonPressed(colorPaletteTitle: String, extractColorPaletteFromCanvas: Boolean) = extendedOnDoneButtonPressed(colorPaletteTitle, extractColorPaletteFromCanvas)

    override fun onBrushTapped(selectedBrush: Brush) = extendedOnBrushTapped(selectedBrush)

    override fun onDoneButtonPressed(radius: String, strength: String) = extendedOnDoneButtonPressed(radius, strength)
}
