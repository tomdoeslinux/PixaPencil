package com.therealbluepandabear.pixapencil.activities.canvas

import android.graphics.Bitmap
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.therealbluepandabear.pixapencil.fragments.colorpicker.ColorPickerFragment
import com.therealbluepandabear.pixapencil.listeners.*
import com.therealbluepandabear.pixapencil.models.BitmapAction
import com.therealbluepandabear.pixapencil.models.Brush
import com.therealbluepandabear.pixapencil.models.ColorPalette
import com.therealbluepandabear.pixapencil.models.Coordinates

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreate()
        configureSavedInstanceState(savedInstanceState)
    }

    fun initColorPickerFragmentInstance(colorPaletteMode: Boolean): ColorPickerFragment {
        return ColorPickerFragment.newInstance(getSelectedColor(), colorPaletteMode)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return extendedOnCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return extendedOnOptionsItemSelected(item)
    }

    override fun onPause() {
        extendedOnPause()
        super.onPause()
    }

    override fun onResume() {
        extendedOnResume()
        super.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        extendedOnSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onPixelTapped(coordinatesTapped: Coordinates) {
        extendedOnPixelTapped(coordinatesTapped)
    }

    override fun onActionUp() {
        extendedOnActionUp()
    }

    override fun onRedoActionCompleted(undoStack: List<BitmapAction>) {
        extendedOnRedoActionCompleted(undoStack)
    }

    override fun onUndoActionCompleted(
        undoStack: List<BitmapAction>,
        bitmapActionData: List<BitmapAction>
    ) {
        extendedOnUndoActionCompleted(undoStack, bitmapActionData)
    }

    override fun onColorTapped(colorTapped: Int, view: View) {
        extendedOnColorTapped(colorTapped, view)
    }

    override fun onColorLongTapped(colorPalette: ColorPalette, colorIndex: Int) {
        extendedOnColorLongTapped(colorPalette, colorIndex)
    }

    override fun onColorAdded(colorPalette: ColorPalette) {
        extendedOnAddColorTapped(colorPalette)
    }

    override fun onDoneButtonPressed(selectedColor: Int, isColorPaletteMode: Boolean) {
        extendedOnDoneButtonPressed(selectedColor, isColorPaletteMode)
    }

    override fun onColorToFindTapped(bitmap: Bitmap, colorToFind: Int): Bitmap {
        return extendedOnColorToFindTapped(bitmap, colorToFind)
    }

    override fun onColorToReplaceTapped(bitmap: Bitmap, colorToReplace: Int): Bitmap {
        return extendedOnColorToReplaceTapped(bitmap, colorToReplace)
    }

    override fun onDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) {
        extendedOnDoneButtonPressed(colorToFind, colorToReplace)
    }

    override fun onBackPressed() {
        extendedOnBackPressed()
    }

    override fun onToolTapped(toolName: String) {
        extendedOnToolTapped(toolName)
    }

    override fun onToolLongTapped(toolName: String) {
        extendedOnToolLongTapped(toolName)
    }

    override fun onFilterTapped(filterName: String) {
        extendedOnFilterSelected(filterName)
    }

    override fun onColorPaletteTapped(selectedColorPalette: ColorPalette) {
        extendedOnColorPaletteTapped(selectedColorPalette)
    }

    override fun onDoneButtonPressed(colorPaletteTitle: String, extractColorPaletteFromCanvas: Boolean) {
        extendedOnDoneButtonPressed(colorPaletteTitle, extractColorPaletteFromCanvas)
    }

    override fun onBrushTapped(selectedBrush: Brush) {
        extendedOnBrushTapped(selectedBrush)
    }

    override fun onDoneButtonPressed(radius: String, strength: String) {
        extendedOnDoneButtonPressed(radius, strength)
    }
}
