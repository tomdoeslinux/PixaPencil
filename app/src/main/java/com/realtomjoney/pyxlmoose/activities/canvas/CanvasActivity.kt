package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.converters.BitmapConverter
import com.realtomjoney.pyxlmoose.fragments.colorpicker.ColorPickerFragment
import com.realtomjoney.pyxlmoose.fragments.outercanvas.OuterCanvasFragment
import com.realtomjoney.pyxlmoose.listeners.*
import com.realtomjoney.pyxlmoose.models.Brush
import com.realtomjoney.pyxlmoose.models.ColorPalette
import com.realtomjoney.pyxlmoose.models.Coordinates
import com.realtomjoney.pyxlmoose.utility.LongConstants

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

    var previewColorToFind: Int? = null
    var previewColorToReplace: Int? = null

    private var prevOrientation: Int = 0
    private var prevBitmapStr: String = ""

    var prevOrientationStr = "PrevOrientation"
    var prevBitmapStrStr = "PrevBitmapStr"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreate()

        if (savedInstanceState != null) {
            prevOrientation = savedInstanceState.getInt(prevOrientationStr)
            prevBitmapStr = savedInstanceState.getString(prevBitmapStrStr)!!
        }
    }

    fun initColorPickerFragmentInstance(colorPaletteMode: Boolean) = ColorPickerFragment.newInstance(getSelectedColor(), colorPaletteMode)

    override fun onCreateOptionsMenu(menu: Menu?) = extendedOnCreateOptionsMenu(menu)

    override fun onOptionsItemSelected(item: MenuItem) = extendedOnOptionsItemSelected(item)

    fun getSelectedColor() = extendedGetSelectedColor()

    fun setPixelColor(color: Int) = if (isPrimaryColorSelected) setPrimaryPixelColor(color) else setSecondaryPixelColor(color)

    private fun onCustomOrientationChanged() {
        for (fragment in supportFragmentManager.fragments) {
            if (fragment is OuterCanvasFragment) {
                supportFragmentManager.beginTransaction()
                    .remove(fragment).commit()
            }
        }

        outerCanvasInstance = OuterCanvasFragment.newInstance(width, height)
        supportFragmentManager.beginTransaction()
            .add(R.id.activityCanvas_outerCanvasFragmentHost, outerCanvasInstance).commit()
    }

    override fun onPause() {
        extendedOnPause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()

        if (prevBitmapStr != "") {
            Handler(Looper.getMainLooper()).postDelayed({
                val convertedBMP = BitmapConverter.convertStringToBitmap(prevBitmapStr)
                if (convertedBMP != null) {
                    outerCanvasInstance.canvasFragment.myCanvasViewInstance.replaceBitmap(convertedBMP)
                }
            }, LongConstants.DEF_HANDLER_DELAY)
        }

        if (prevOrientation != 0) {
            if (prevOrientation != resources.configuration.orientation) {
                onCustomOrientationChanged()
                prevOrientation = resources.configuration.orientation
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(prevOrientationStr, resources.configuration.orientation)
        outState.putString(prevBitmapStrStr, BitmapConverter.convertBitmapToString(outerCanvasInstance.canvasFragment.myCanvasViewInstance.pixelGridViewBitmap))
        super.onSaveInstanceState(outState)
    }

    override fun onPixelTapped(coordinatesTapped: Coordinates) = extendedOnPixelTapped(coordinatesTapped)

    override fun onActionUp() = extendedOnActionUp()

    override fun onColorTapped(colorTapped: Int, view: View) = extendedOnColorTapped(colorTapped, view)

    override fun onColorLongTapped(colorPalette: ColorPalette, colorIndex: Int) = extendedOnColorLongTapped(colorPalette, colorIndex)

    override fun onColorAdded(colorPalette: ColorPalette) = extendedOnAddColorTapped(colorPalette)

    override fun onDoneButtonPressed(selectedColor: Int, isColorPaletteMode: Boolean) = extendedOnDoneButtonPressed(selectedColor, isColorPaletteMode)

    override fun onColorToFindTapped(bitmap: Bitmap, colorToFind: Int): Bitmap = extendedOnColorToFindTapped(bitmap, colorToFind)

    override fun onColorToReplaceTapped(bitmap: Bitmap, colorToReplace: Int) = extendedOnColorToReplaceTapped(bitmap, colorToReplace)

    override fun onDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) = extendedOnDoneButtonPressed(colorToFind, colorToReplace)

    override fun onBackPressed() = extendedOnBackPressed()

    override fun onToolTapped(toolName: String) = extendedOnToolTapped(toolName)

    override fun onFilterTapped(filterName: String) = extendedOnFilterSelected(filterName)

    override fun onColorPaletteTapped(selectedColorPalette: ColorPalette) = extendedOnColorPaletteTapped(selectedColorPalette)

    override fun onDoneButtonPressed(colorPaletteTitle: String, extractColorPaletteFromCanvas: Boolean) = extendedOnDoneButtonPressed(colorPaletteTitle, extractColorPaletteFromCanvas)

    override fun onBrushTapped(selectedBrush: Brush) = extendedOnBrushTapped(selectedBrush)

    override fun onDoneButtonPressed(radius: String, strength: String) = extendedOnDoneButtonPressed(radius, strength)
}
