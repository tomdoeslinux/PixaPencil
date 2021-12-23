package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.RectF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import com.realtomjoney.pyxlmoose.customviews.mycanvasview.MyCanvasView
import com.realtomjoney.pyxlmoose.fragments.colorpicker.ColorPickerFragment
import com.realtomjoney.pyxlmoose.listeners.*
import com.realtomjoney.pyxlmoose.models.ColorPalette

class CanvasActivity : AppCompatActivity(), CanvasFragmentListener, ColorPickerListener, ColorPickerFragmentListener, FindAndReplaceFragmentListener, ToolsFragmentListener, FiltersFragmentListener, ColorPalettesFragmentListener, NewColorPaletteFragmentListener {
    var previousView: View? = null

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        extendedOnCreate()
    }

    fun initColorPickerFragmentInstance(colorPaletteMode: Boolean) = ColorPickerFragment.newInstance(getSelectedColor(), colorPaletteMode)

    override fun onCreateOptionsMenu(menu: Menu?) = extendedOnCreateOptionsMenu(menu)

    override fun onOptionsItemSelected(item: MenuItem) = extendedOnOptionsItemSelected(item)

    fun getSelectedColor() = if (isPrimaryColorSelected) primaryColor else secondaryColor

    fun setColors() = extendedSetColors()

    fun setPixelColor(color: Int) = if (isPrimaryColorSelected) setPrimaryPixelColor(color) else setSecondaryPixelColor(color)

    private fun setPrimaryPixelColor(color: Int) = extendedSetPrimaryPixelColor(color)

    private fun setSecondaryPixelColor(color: Int) = extendedSetSecondaryPixelColor(color)

    override fun onPause() {
        extendedOnPause()
        super.onPause()
    }

    fun setOnClickListeners() = extendedSetOnClickListeners()

    fun setUpRecyclerView() = extendedSetUpRecyclerView()

    fun setUpFragment() = extendedSetUpFragment()

    fun setBindings() = extendedSetBindings()

    override fun onPixelTapped(canvasInstance: MyCanvasView, rectTapped: RectF) = extendedOnPixelTapped(canvasInstance, rectTapped)

    override fun onActionUp() = extendedOnActionUp()

    fun updateColorSelectedIndicator(it: View) = extendedUpdateColorSelectedIndicator(it)

    override fun onColorTapped(colorTapped: Int, view: View) = extendedOnColorTapped(colorTapped, view)

    override fun onColorAdded(colorPalette: ColorPalette) = extendedOnAddColorTapped(colorPalette)

    override fun onDoneButtonPressed(selectedColor: Int, isColorPaletteMode: Boolean) = extendedOnDoneButtonPressed(selectedColor, isColorPaletteMode)

    override fun onDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) = extendedOnDoneButtonPressed(colorToFind, colorToReplace)

    override fun onBackPressed() = extendedOnBackPressed()

    override fun onToolTapped(toolName: String) = extendedOnToolTapped(toolName)

    override fun onFilterTapped(filterName: String) = extendedOnFilterSelected(filterName)

    override fun onColorPaletteTapped(selectedColorPalette: ColorPalette) = extendedOnColorPaletteTapped(selectedColorPalette)

    override fun onDoneButtonPressed(colorPaletteTitle: String) = extendedOnDoneButtonPressed(colorPaletteTitle)
}
