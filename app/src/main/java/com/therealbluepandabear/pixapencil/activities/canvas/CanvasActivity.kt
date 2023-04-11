/*
 * PixaPencil
 * Copyright 2022  therealbluepandabear
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.therealbluepandabear.pixapencil.activities.canvas

import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.therealbluepandabear.pixapencil.activities.canvas.oncreate.root.onCreate
import com.therealbluepandabear.pixapencil.activities.canvas.ondonebuttonpressed.extendedOnDoneButtonPressed
import com.therealbluepandabear.pixapencil.activities.canvas.ontapped.*
import com.therealbluepandabear.pixapencil.activities.canvas.viewmodel.CanvasActivityViewModel
import com.therealbluepandabear.pixapencil.adapters.ColorPaletteColorPickerAdapter
import com.therealbluepandabear.pixapencil.algorithms.*
import com.therealbluepandabear.pixapencil.databinding.ActivityCanvasBinding
import com.therealbluepandabear.pixapencil.listeners.*
import com.therealbluepandabear.pixapencil.models.*
import com.therealbluepandabear.pixapencil.utility.constants.IntConstants
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants


var selectedColorPaletteIndex: Int = 0

class CanvasActivity :
    AppCompatActivity(),
    ColorPaletteColorPickerListener,
    ColorPickerFragmentListener,
    ToolsFragmentListener,
    FiltersFragmentListener,
    ColorPalettesFragmentListener,
    NewColorPaletteFragmentListener,
    BrushesFragmentListener,
    SprayToolSettingsFragmentListener,
    DitherToolSettingsFragmentListener {

    // Algorithm instances:
    lateinit var lineAlgorithm: LineAlgorithm
    lateinit var circleAlgorithm: CircleAlgorithm
    lateinit var filledCircleAlgorithm: CircleAlgorithm
    lateinit var ellipseAlgorithm: EllipseAlgorithm
    lateinit var filledEllipseAlgorithm: EllipseAlgorithm
    lateinit var rectangleAlgorithm: RectangleAlgorithm
    lateinit var filledRectangleAlgorithm: RectangleAlgorithm
    lateinit var visibleRectanglePreviewAlgorithm: RectanglePreviewAlgorithm
    lateinit var visibleSquarePreviewAlgorithm: SquarePreviewAlgorithm
    lateinit var invisibleRectanglePreviewAlgorithm: RectanglePreviewAlgorithm
    lateinit var invisibleSquarePreviewAlgorithm: SquarePreviewAlgorithm

    // Binding:
    lateinit var binding: ActivityCanvasBinding

    // View model:
    val viewModel: CanvasActivityViewModel by viewModels()

    // Intent info:
    var index: Int = -1
    var projectTitle: String = ""
    var width: Int = IntConstants.DEFAULT_BITMAP_DIMEN
    var height: Int = IntConstants.DEFAULT_BITMAP_DIMEN
    var uri: Uri? = null

    var background: Drawable? = null

    lateinit var menu: Menu

    var shapeOrigin: Coordinate? = null
    var coordinate: Coordinate? = null
    var firstShapeDrawn = false

    lateinit var sharedPreferenceObject: SharedPreferences

    lateinit var sprayAlgorithmInstance: SprayAlgorithm
    var sprayAlgorithmInstanceInitialized = ::sprayAlgorithmInstance.isInitialized

    lateinit var primaryAlgorithmInfoParameter: AlgorithmInfoParameter
    val primaryAlgorithmInfoParameterInitialized = ::primaryAlgorithmInfoParameter.isInitialized

    var shadingToolMode = StringConstants.ShadingToolModes.LIGHTEN_SHADING_TOOL_MODE

    var sharedPreferenceShowSprayToolTip = true
    var sharedPreferenceShowShadingToolTip = true
    var sharedPreferenceShowDitherToolTip = true

    inner class CanvasCommandsHelper(val baseReference: CanvasActivity = this@CanvasActivity)

    val canvasCommandsHelperInstance: CanvasCommandsHelper = CanvasCommandsHelper()

    lateinit var adapter: ColorPaletteColorPickerAdapter

    var shapePreviewCache: List<BitmapActionData> = listOf()

    fun clearPreviousShapePreview() {
        for (actionData in shapePreviewCache.distinctBy { it.coordinate }) {
            binding.activityCanvasDrawingView.drawingViewBitmap.setPixel(
                actionData.coordinate.x,
                actionData.coordinate.y,
                actionData.colorAtPosition)
        }

        shapePreviewCache = listOf()
        viewModel.currentBitmapAction?.actionData?.clear()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreate()
    }

    override fun onPause() {
        extendedOnPause()
        super.onPause()
    }

    override fun onColorTapped(colorTapped: Int) {
        extendedOnColorTapped(colorTapped)
    }

    override fun onColorLongTapped(colorPalette: ColorPalette, colorIndex: Int) {
        extendedOnColorLongTapped(colorPalette, colorIndex)
    }

    override fun onColorAdded(colorPalette: ColorPalette) {
        extendedOnAddColorTapped(colorPalette)
    }

    override fun onDoneButtonPressed(selectedColor: Int, colorPalette: ColorPalette?) {
        extendedOnDoneButtonPressed(selectedColor, colorPalette)
    }

    override fun onToolTapped(toolName: String) {
        extendedOnToolTapped(toolName)
    }

    override fun onToolLongTapped(toolName: String) {
        extendedOnToolLongTapped(toolName)
    }

    override fun onFilterTapped(filterName: String) {
        extendedOnFilterTapped(filterName)
    }

    override fun onColorPaletteTapped(selectedColorPalette: ColorPalette) {
        extendedOnColorPaletteTapped(selectedColorPalette)
    }

    override fun onColorPaletteLongTapped(selectedColorPalette: ColorPalette) {
        extendedOnColorPaletteLongTapped(selectedColorPalette)
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

    override fun onDitherBrushTapped(ditherBrush: DitherBrush) {
        viewModel.currentDitherBrush = ditherBrush
        supportFragmentManager.popBackStackImmediate()

        binding.root.post {
            judgeUndoRedoStacks()
        }
    }
}
